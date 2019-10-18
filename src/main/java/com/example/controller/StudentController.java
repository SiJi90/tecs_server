package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Grade;
import com.example.beans.Student;
import com.example.beans.Teacher;
import com.example.beans.TeacherGrade;
import com.example.service.*;
import com.example.util.ExportExcelUtil;
import com.example.util.ExportExcelWrapper;
import com.example.util.Msg;
import com.example.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private TeacherGradeService teacherGradeService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @Autowired
    private CourseService courseService;

    /**
     * 获取所有学生
     *
     * @return 返回所有学生集合
     */
    @GetMapping("/list")
    public Msg getAllStudent(String name) {
        List<Student> students = new ArrayList<>();
        if (name.matches("[0-9]+")) {
            students = studentService.list(new QueryWrapper<Student>().like("student_no", Integer.parseInt(name)));
        } else {
            students = studentService.list(new QueryWrapper<Student>().like("student_name", name));
        }
        List<Integer> greadList = new ArrayList<>();
        for (Student student : students) {
            greadList.add(student.getStudentGradeId());
        }
        Collection<Grade> grades = gradeService.listByIds(greadList);
        return Msg.success().add("list", students).add("grades", grades);
    }

    /**
     * 单个 | 批量 删除二合一
     *
     * @param nos 学生学号
     * @return 成功 | 失败
     */
    @DeleteMapping("/del")
    public Msg delStudent(String nos) {
        if (nos.contains(",")) {
            // 批量删除
            String[] list = nos.split(",");
            boolean res = studentService.remove(new QueryWrapper<Student>()
                    .in("student_no", Arrays.asList(list)));

            if (res) {
                return Msg.success();
            }
        } else {
            boolean res = studentService.remove(new QueryWrapper<Student>()
                    .eq("student_no", nos));
            if (res) {
                return Msg.success();
            }
        }
        return Msg.fail();
    }

    @GetMapping("/teacher")
    public Msg getTeacher(Integer no) {
        // 查询这个学生信息
        Student student = studentService.getOne(
                new QueryWrapper<Student>().eq("student_no", no)
        );
        // 根据学生班级 id 查询这个班级的所有教师
        List<TeacherGrade> teacherGrade = teacherGradeService.list(
                new QueryWrapper<TeacherGrade>().eq("grade_id", student.getStudentGradeId())
        );

        // 获取所有教师 id
        List<Integer> ids = new ArrayList();
        for (int i = 0; i < teacherGrade.size(); i++) {
            ids.add(Integer.parseInt(teacherGrade.get(i).getTeacherId()));
        }

        // 根据教师 id 查找所教授的课程
    /*    Map<Integer, Object> map = new HashMap<>();
        for (Integer id : ids) {
            List<TeacherCourse> teacherCourses = teacherCourseService.list(
                    new QueryWrapper<TeacherCourse>().eq("teacher_id", id)
            );
            List<Integer> list = new ArrayList<>();
            for (TeacherCourse teacherCourse : teacherCourses) {
                list.add(teacherCourse.getCourseId());
            }
            List<Course> courses = courseService.list(
                    new QueryWrapper<Course>().in("course_id", list)
            );
            map.put(id, courses);
        }
*/
        // 根据课程 id 查询课程名


        // 根据教师 id 查找对应的教师信息
        List<Teacher> teachers = teacherService.list(
                new QueryWrapper<Teacher>().in("teacher_no", ids)
        );

        return Msg.success().add("list", teachers);
    }

    /**
     * 文件上传学生信息
     */
    @PostMapping("/file")
    public String studentFile(@RequestParam(value = "filename") MultipartFile filename) throws Exception {
        //判断前端是否传入文件
        System.out.println(filename.getOriginalFilename());
        if (filename.isEmpty()) {
            return "文件不能为空";
        }
        //文件输入流
        InputStream inputStream = filename.getInputStream();
        //写入文件字节流和文件名，在service里面对文件格式进行处理
        List<List<Object>> list = Util.getBankListByExcel(inputStream, filename.getOriginalFilename());
        //关闭流
        inputStream.close();
        //对excel从第二行开始循环遍历,插入数据库
        for (int i = 0; i < list.size(); i++) {
            //遍历每一条记录
            List<Object> lo = list.get(i);
            //转换成字符串
            String s = String.valueOf(lo);
            s = s.substring(1, s.length() - 1);
            //转换成String数组
            String[] students = s.split(",");
            boolean res = studentService.save(new Student((int) Double.parseDouble(students[0]), students[1],
                    String.valueOf(students[2]).trim(), students[3], (int) Double.parseDouble(students[4]),
                    (int) Double.parseDouble(students[5]), (int) Double.parseDouble(students[6])));
            System.out.println(res);
            //String s1=String.valueOf((int )Double.parseDouble(students[1]));
            //System.out.println("结果为："+ s1);
        }
        return "导入成功";
    }


    /**
     * 导出 Excel 方法
     *
     * @param response HttpServletResponse 对象
     * @return 成功或失败
     * @throws Exception
     */
    @GetMapping(value = "/getFile")
    public Msg getExcel(HttpServletResponse response) throws Exception {
        //准备数据
        List<Student> list = studentService.list();
/*        for (Student student : list) {
            System.out.println(student);
        }*/

        String[] columnNames = {"学生ID", "学生编号", "学生姓名", "学生密码", " 性别", "班级编号", "状态", "角色"};
        String title = "学生信息表";
        ExportExcelWrapper<Student> util = new ExportExcelWrapper<>();
        util.exportExcel(title, title, columnNames, list, response, ExportExcelUtil.EXCEL_FILE_2007);
       return Msg.success();
    }


}


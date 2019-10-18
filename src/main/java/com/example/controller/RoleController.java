package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Admin;
import com.example.beans.Role;
import com.example.beans.Student;
import com.example.beans.Teacher;
import com.example.service.AdminService;
import com.example.service.RoleService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询角色表所有信息
     *
     * @return Msg对象, 包含状态和角色表数据
     */
    @GetMapping("/roles")
    public Msg getRoles() {
        List<Role> roleList = roleService.list();
        return Msg.success().add("roles", roleList);
    }


    /**
     * 处理登录逻辑
     *
     * @param roleId 角色id
     * @param no     账号
     * @param pass   密码
     * @return 成功与否
     */
    @PostMapping("/login")
    public Msg checkLogin(Integer roleId, String no, String pass) {

        if ("Admin".equals(no)) {
            no = "1000";
        }

        if (roleId == 1) {
            Admin admin = adminService.getOne(
                    new LambdaQueryWrapper<Admin>()
                            .eq(Admin::getAdminCount, Integer.parseInt(no))
                            .eq(Admin::getAdminPassword, pass)
                            .eq(Admin::getRoleId, roleId));
            if (admin != null) {
                return Msg.success().add("user", admin);
            } else {
                return Msg.fail();
            }

        } else if (roleId == 2 || roleId == 3) {
            Teacher teacher = teacherService.getOne(
                    new LambdaQueryWrapper<Teacher>()
                            .eq(Teacher::getTeacherNo, Integer.parseInt(no))
                            .eq(Teacher::getTeacherPassword, pass)
                            .eq(Teacher::getTeacherRoleId, roleId));
            if (teacher != null) {
                return Msg.success().add("user", teacher);
            } else {
                return Msg.fail();
            }
        } else {
            Student student = studentService.getOne(
                    new LambdaQueryWrapper<Student>()
                            .eq(Student::getStudentNo, Integer.parseInt(no))
                            .eq(Student::getStudentPassword, pass)
                            .eq(Student::getStudentRoleId, roleId));
            if (student != null) {
                return Msg.success().add("user", student);
            } else {
                return Msg.fail();
            }
        }

    }

    /**
     * 将用户设为已评价
     *
     * @param no     用户账号
     * @param roleId 角色
     * @return success
     */
    @PostMapping("/status")
    public Msg setStatus(Integer no, Integer roleId) {
        if (roleId == 4) {
            Student student = studentService.getOne(
                    new QueryWrapper<Student>().eq("student_no", no)
            );
            student.setStudentStatus(1);
            studentService.update(student, new QueryWrapper<Student>().eq("student_no", no));
        } else {
            Teacher teacher = teacherService.getOne(
                    new QueryWrapper<Teacher>().eq("teacher_no", no)
            );
            teacher.setTeacherStatus(1);
            teacherService.update(teacher, new QueryWrapper<Teacher>().eq("teacher_no", no));
        }
        return Msg.success();
    }

    /**
     * 根据条件查询教师或学生信息
     *
     * @param no     账号
     * @param roleId 用户类型
     * @return success
     */
    @GetMapping("/list")
    public Msg list(Integer no, Integer roleId) {
        List list = new ArrayList();
        if (roleId == 4) {
            list = studentService.list(
                    new QueryWrapper<Student>().like("student_no", no)
            );
        } else {
            list = teacherService.list(
                    new QueryWrapper<Teacher>().like("teacher_no", no)
            );
        }
        return Msg.success().add("list", list);
    }

    @PutMapping("/reset")
    public Msg resetPass(Integer no, Integer roleId) {
        boolean res = false;
        if (roleId == 4) {
            Student student = studentService.getOne(
                    new QueryWrapper<Student>().eq("student_no", no)
            );
            student.setStudentPassword(String.valueOf(student.getStudentNo()));
            res = studentService.update(student, new QueryWrapper<Student>().eq("student_no", no));
        } else {
            Teacher teacher = teacherService.getOne(
                    new QueryWrapper<Teacher>().eq("teacher_no", no)
            );
            teacher.setTeacherPassword(String.valueOf(teacher.getTeacherNo()));
            res = teacherService.update(teacher,
                    new QueryWrapper<Teacher>().eq("teacher_no", no)
            );
        }
        if (res) {
            return Msg.success();
        }
        return Msg.fail();
    }
}


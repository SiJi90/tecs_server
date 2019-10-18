package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Teacher;
import com.example.service.TeacherService;
import com.example.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 教师表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询方法
     *
     * @param pn 查询的页码
     * @return 分页显示教师数据
     */
    @GetMapping("/{pn}")
    public Msg getTeacher(@PathVariable(value = "pn") Integer pn, String value) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 6);
        // 查询所有教师信息
        List<Teacher> listTeacher = new ArrayList<>();
        if (value.matches("[0,9]+")) {
            listTeacher = teacherService.list(
                    new QueryWrapper<Teacher>().like("teacher_no", Integer.parseInt(value))
                            .or()
                            .like("teacher_tel", Integer.parseInt(value))
            );
        } else if (value.contains("讲") || value.contains("师")) {
            listTeacher = teacherService.list(
                    new QueryWrapper<Teacher>().eq("teacher_role_id", 3)
                            .or()
                            .like("teacher_name", value)
            );
        } else if (value.contains("教") || value.contains("授")) {
            listTeacher = teacherService.list(
                    new QueryWrapper<Teacher>().eq("teacher_role_id", 2)
                            .or()
                            .like("teacher_name", value)
            );
        } else if (value.contains("男") || value.contains("女")) {
            listTeacher = teacherService.list(
                    new QueryWrapper<Teacher>().like("teacher_sex", value)
                            .or()
                            .like("teacher_name", value)
            );
        } else {

            listTeacher = teacherService.list(
                    new QueryWrapper<Teacher>().like("teacher_name", value)
            );
        }

        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo pageInfo = new PageInfo(listTeacher, 5);

        return Msg.success().add("pageInfo", pageInfo);
    }

    /**
     * 单个删除
     *
     * @param no 待删除教师 no
     * @return 成功 | 失败
     */
    @DeleteMapping("/del/{no}")
    public Msg delTeacher(@PathVariable("no") Integer no) {
        boolean res = teacherService.remove(new QueryWrapper<Teacher>().eq("teacher_No", no));
        if (res) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 删除多个教师信息
     *
     * @param listNo 教师工号集合
     * @return 成功 200 | 失败 100
     */
    @DeleteMapping("/delAll")
    public Msg delAllTeacher(String listNo) {
        String[] list = listNo.split(",");
        boolean res = teacherService.remove(new QueryWrapper<Teacher>().in("teacher_no", Arrays.asList(list)));
        if (res) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 教师修改
     *
     * @param teacher 教师信息
     * @return 成功 true| 失败 false
     */
    @PutMapping("/update")
    public Msg updateTeacher(Teacher teacher) {
        // 向数据库更新教师信息
        boolean res = teacherService.updateById(teacher);
        // 返回消息
        if (res) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 添加教师信息
     *
     * @param teacher 教师信息
     * @return success
     */
    @PostMapping("/add")
    public Msg addTeacher(Teacher teacher) {
        boolean res = teacherService.save(teacher);
        if (res) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 查看教师评价信息
     *
     * @param no     账号
     * @param roleId 角色
     * @return List
     */
    @GetMapping("listEvaluate")
    public Msg getListEvaluate(Integer no, Integer roleId) {
        List<Teacher> list = new ArrayList<>();
        if (roleId == 2) {
            // 这是领导, 可以评价所有教师
            list = teacherService.list(
                    new QueryWrapper<Teacher>().eq("teacher_role_id", 3)
            );
        } else {
            // 这是教师
            list = teacherService.list(
                    new QueryWrapper<Teacher>().eq("teacher_role_id", 3)
                            .ne("teacher_no", no)
            );
        }
        return Msg.success().add("list", list);
    }
}


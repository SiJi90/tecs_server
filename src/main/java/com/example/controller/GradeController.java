package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Grade;
import com.example.beans.Student;
import com.example.service.GradeService;
import com.example.service.StudentService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 班级表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    /**
     * 获取所有班级信息
     *
     * @param name 可以根据条件进行查找(课程名称)
     * @return success
     */
    @GetMapping("/list")
    public Msg getList(String name) {
        // 查询所有班级
        List<List> lists = new ArrayList<>();
        List<Grade> list = gradeService.list(new QueryWrapper<Grade>().like("grade_name", name));

        lists.addAll(Collections.singleton(list));
        Map<String, Object> students = new HashMap<>();

        for (Grade grade : list) {
            // 统计每个班级人数
            int studentNum = studentService.count(new QueryWrapper<Student>().eq("student_grade_id", grade.getGradeId()));
            students.put(String.valueOf(grade.getGradeId()), studentNum);
        }

        List list1 = new ArrayList<>(students.values());
        lists.addAll(list1);
        return Msg.success().add("list", list);

    }
}


package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Course;
import com.example.service.CourseService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public Msg getList(String name) {
        List<Course> list = courseService.list(new QueryWrapper<Course>().like("course_name", name));
        return Msg.success().add("list", list);
    }

}


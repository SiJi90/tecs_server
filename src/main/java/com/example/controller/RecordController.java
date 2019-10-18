package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Record;
import com.example.beans.Teacher;
import com.example.service.RecordService;
import com.example.service.TeacherService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>
 * 评分记录表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public Msg add(Integer teacherId, Integer score) {
        boolean res = recordService.save(new Record(teacherId, score));
        return Msg.success();
    }

    @GetMapping("/list")
    public Msg list() {
        // 遍历所有记录
        List<Record> list = recordService.list();
        // 存放返回前段的结果
        List<Map<String, Object>> newList = new ArrayList<>();
        // 去除重复的教师 no
        Set<Integer> listNo = new HashSet<>();
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < list.size(); i++) {
            listNo.add(list.get(i).getTeacherId());
        }
        for (Integer id : listNo) {
            // 根据教师 no 查询所有记录
            List<Record> listId = recordService.list(
                    new QueryWrapper<Record>().eq("teacher_id", id)
            );
            double score = 0;
            // 遍历这个教师所有记录, 求出其平均成绩
            for (int i = 0; i < listId.size(); i++) {
                score += listId.get(i).getCoutScore();
            }
            // 算出平均分
            score = score / listId.size();
            //System.out.println(df.format(score));
            Map<String, Object> map = new HashMap<>();
            // 根据 教师 id 查出 教师相关信息
            Teacher teacher = teacherService.getOne(
                    new QueryWrapper<Teacher>().eq("teacher_no", id)
            );
            // 封装这个教师所有信息
            map.put("teacherNo", id);
            map.put("score", df.format(score));
            map.put("teacherName", teacher.getTeacherName());
            // 将封装好的信息加入到返回信息中
            newList.add(map);
        }
        // 存放所有未被评价老师的信息
        // 查询所有未被评价的老师
        List<Teacher> teachers = teacherService.list(
                new QueryWrapper<Teacher>().notIn("teacher_no", listNo)
        );
        // 遍历所有未被评价的老师
        for (int i = 0; i < teachers.size(); i++) {
            Map<String, Object> mapNotEvaluate = new HashMap<>();
            Teacher teacherNotEvaluate = teachers.get(i);
            mapNotEvaluate.put("teacherNo", teacherNotEvaluate.getTeacherNo());
            mapNotEvaluate.put("score", 0);
            mapNotEvaluate.put("teacherName", teacherNotEvaluate.getTeacherName());

            newList.add(mapNotEvaluate);
        }


        // 返回所有封装好的信息
        return Msg.success().add("list", newList);
    }

}


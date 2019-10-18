package com.example.service.impl;

import com.example.beans.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

}

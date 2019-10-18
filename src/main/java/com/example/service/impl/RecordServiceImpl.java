package com.example.service.impl;

import com.example.beans.Record;
import com.example.mapper.RecordMapper;
import com.example.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评分记录表 服务实现类
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}

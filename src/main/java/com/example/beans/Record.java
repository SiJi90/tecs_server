package com.example.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 评分记录表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Record extends Model<Record> {

    private static final long serialVersionUID=1L;

    /**
     * 记录编号
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 被评人
     */
    @TableField("teacher_id")
    private Integer teacherId;

    /**
     * 总分数
     */
    @TableField("cout_score")
    private Integer coutScore;


    public Record(Integer teacherId, Integer coutScore) {
        this.teacherId = teacherId;
        this.coutScore = coutScore;
    }

    public Record() {

    }

    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }

}

package com.example.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Course extends Model<Course> {

    private static final long serialVersionUID=1L;

    /**
     * 课程id
     */
    @TableId(value = "course_Id", type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程名称
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 学时
     */
    @TableField("course_hour")
    private Integer courseHour;

    /**
     * 学分
     */
    @TableField("course_credit")
    private Integer courseCredit;


    @Override
    protected Serializable pkVal() {
        return this.courseId;
    }

}

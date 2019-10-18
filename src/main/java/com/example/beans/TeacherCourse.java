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
 * 教师-课程关系表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherCourse extends Model<TeacherCourse> {

    private static final long serialVersionUID=1L;

    /**
     * 自增id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 教师id
     */
    @TableField("teacher_id")
    private Integer teacherId;

    /**
     * 课程id
     */
    @TableField("course_id")
    private Integer courseId;


    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

}

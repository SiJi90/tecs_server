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
 * 教师-班级关系表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherGrade extends Model<TeacherGrade> {

    private static final long serialVersionUID=1L;

    /**
     * 课程编号
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 教师id
     */
    @TableField("teacher_id")
    private String teacherId;

    /**
     * 用户id
     */
    @TableField("grade_id")
    private Integer gradeId;


    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

}

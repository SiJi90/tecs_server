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
 * 教师表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;

    /**
     * 工号
     */
    @TableField("teacher_no")
    private Integer teacherNo;

    /**
     * 名字
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 密码
     */
    @TableField("teacher_password")
    private String teacherPassword;

    /**
     * 性别
     */
    @TableField("teacher_sex")
    private String teacherSex;

    /**
     * 0:未评价 1:已评价
     */
    @TableField("teacher_status")
    private Integer teacherStatus;

    /**
     * 职位
     */
    @TableField("teacher_role_id")
    private Integer teacherRoleId;

    /**
     * 联系方式
     */
    @TableField("teacher_tel")
    private String teacherTel;

    /**
     * 生日
     */
    @TableField("teacher_birth")
    private String teacherBirth;


    @Override
    protected Serializable pkVal() {
        return this.teacherId;
    }

}

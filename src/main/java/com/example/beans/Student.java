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
 * 学生表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Student extends Model<Student> {

    //private static final long serialVersionUID=1L;

    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @TableField("student_no")
    private Integer studentNo;

    /**
     * 姓名
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 密码
     */
    @TableField("student_password")
    private String studentPassword;

    /**
     * 性别
     */
    @TableField("student_sex")
    private String studentSex;

    /**
     * 班级id
     */
    @TableField("student_grade_id")
    private Integer studentGradeId;

    @TableField("student_status")
    private Integer studentStatus;

    @TableField("student_role_id")
    private Integer studentRoleId;


    public Student() {

    }

    public Student(Integer studentNo, String studentName, String studentPassword, String studentSex, Integer studentGradeId, Integer studentStatus, Integer studentRoleId) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentPassword = studentPassword;
        this.studentSex = studentSex;
        this.studentGradeId = studentGradeId;
        this.studentStatus = studentStatus;
        this.studentRoleId = studentRoleId;
    }

    @Override
    protected Serializable pkVal() {
        return this.studentId;
    }

}

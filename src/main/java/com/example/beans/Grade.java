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
 * 班级表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Grade extends Model<Grade> {

    private static final long serialVersionUID=1L;

    /**
     * 班级id
     */
    @TableId(value = "grade_Id", type = IdType.AUTO)
    private Integer gradeId;

    /**
     * 班级名
     */
    @TableField("grade_name")
    private String gradeName;


    @Override
    protected Serializable pkVal() {
        return this.gradeId;
    }

}

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
 * 评价标准选项表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluateItem extends Model<EvaluateItem> {

    private static final long serialVersionUID=1L;

    /**
     * 评判标准编号
     */
    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    /**
     * 标准描述
     */
    @TableField("item_desc")
    private String itemDesc;

    @TableField("item_type")
    private Integer itemType;

    /**
     * 选项1
     */
    @TableField("option_1")
    private String option1;

    /**
     * 选项2
     */
    @TableField("option_2")
    private String option2;

    /**
     * 选项3
     */
    @TableField("option_3")
    private String option3;

    /**
     * 选项4
     */
    @TableField("option_4")
    private String option4;


    @Override
    protected Serializable pkVal() {
        return this.itemId;
    }

}

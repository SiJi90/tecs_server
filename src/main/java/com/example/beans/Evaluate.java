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
 * 评价表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Evaluate extends Model<Evaluate> {

    private static final long serialVersionUID=1L;

    @TableId(value = "evaluate_id", type = IdType.AUTO)
    private Integer evaluateId;

    /**
     * 问题编号
     */
    @TableField("evaluate_item_id")
    private String evaluateItemId;

    /**
     * 问题类型
     */
    @TableField("evaluate_type")
    private Integer evaluateType;

    public Evaluate(String evaluateItemId, Integer evaluateType) {
        this.evaluateItemId = evaluateItemId;
        this.evaluateType = evaluateType;
    }

    public Evaluate() {

    }

    @Override
    protected Serializable pkVal() {
        return this.evaluateId;
    }

}

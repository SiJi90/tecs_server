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
 * 角色表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    /**
     * 角色id号
     */
    @TableId(value = "role_Id", type = IdType.AUTO)
    private Integer roleId;

    @TableField("role_name")
    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}

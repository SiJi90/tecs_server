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
 * 管理员表
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin extends Model<Admin> {

    private static final long serialVersionUID=1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    /**
     * 账号
     */
    @TableField("admin_count")
    private Integer adminCount;

    /**
     * 密码
     */
    @TableField("admin_password")
    private String adminPassword;

    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

}

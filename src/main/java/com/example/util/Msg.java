package com.example.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 公用消息返回类
 *
 * @author: SiJi
 * @date: 2019/09/25
 */
@Data
public class Msg {
    private Integer code;
    private String msg;
    private Map<String, Object> extend = new HashMap<String, Object>();

    /**
     * 成功
     *
     * @return 成功
     */
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
    }

    /**
     * 失败
     *
     * @return 成请求失败
     */
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理失败");
        return result;
    }

    /**
     * 保存信息
     *
     * @param key   键名
     * @param value 值
     * @return 一个 Msg
     */
    public Msg add(String key, Object value) {
        getExtend().put(key, value);
        return this;
    }
}

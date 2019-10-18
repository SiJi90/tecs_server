package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.EvaluateItem;
import com.example.service.EvaluateItemService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 评价标准选项表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/evaluateItem")
public class EvaluateItemController {

    @Autowired
    private EvaluateItemService evaluateItemService;

    /**
     * 根据条件模糊查询表中所有信息
     *
     * @param value 从前台接受到的条件
     * @return 查询到的数据
     */
    @GetMapping("/list")
    public Msg getList(String value) {
        // 查询这个表的所有信息
        List<EvaluateItem> list = evaluateItemService.list(
                new QueryWrapper<EvaluateItem>().like("item_desc", value)
        );
        return Msg.success().add("list", list);
    }

    /**
     * 删除操作  批量 | 单个删除 二合一
     *
     * @param id 前台传递过来的 id
     * @return success | fail
     */
    @DeleteMapping("/del")
    public Msg del(String id) {
        // 标志是否成功删除
        boolean res = false;
        if (id.contains(",")) {
            // 执行批量删除
            String[] ids = id.split(",");
            res = evaluateItemService.remove(
                    new QueryWrapper<EvaluateItem>().in("item_id", Arrays.asList(ids))
            );
        } else {
            // 执行单个删除
            res = evaluateItemService.remove(
                    new QueryWrapper<EvaluateItem>().eq("item_id", Integer.parseInt(id))
            );
        }
        if (res) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }
}


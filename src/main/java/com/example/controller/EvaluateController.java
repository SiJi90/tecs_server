package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.beans.Evaluate;
import com.example.beans.EvaluateItem;
import com.example.service.EvaluateItemService;
import com.example.service.EvaluateService;
import com.example.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author siji
 * @since 2019-09-25
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private EvaluateItemService evaluateItemService;

    /**
     * 创建评价表
     *
     * @param ids  表的所有题目
     * @param type 表的类型
     * @return success | fail
     */
    @PostMapping("/table")
    public Msg creatTable(String ids, Integer type) {
        boolean res = evaluateService.save(new Evaluate(ids, type));
        if (res) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 获取所有评价表
     *
     * @return 评价表数据
     */
    @GetMapping("/list")
    public Msg list() {
        List<Evaluate> list = evaluateService.list();
        return Msg.success().add("list", list);
    }

    /**
     * 单个 | 批量删除 二合一
     *
     * @param id 前段传的要删除 id 集合
     * @return success | fail
     */
    @DeleteMapping("/del")
    public Msg del(String id) {
        boolean res = false;
        if (id.contains(",")) {
            // 批量删除
            String[] ids = id.split(",");
            res = evaluateService.remove(
                    new QueryWrapper<Evaluate>().in("evaluate_id", Arrays.asList(ids))
            );
        } else {
            res = evaluateService.remove(
                    new QueryWrapper<Evaluate>().eq("evaluate_id", id)
            );
        }
        if (res) {
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 根据角色不同显示不同的角色表
     *
     * @param id 请求的评价表类型
     * @return 评价表
     */
    @GetMapping("/view")
    public Msg view(Integer id) {
        Evaluate evaluate = evaluateService.getOne(
                new QueryWrapper<Evaluate>().eq("evaluate_type", id)
        );
        String[] itemIds = evaluate.getEvaluateItemId().split(",");
        String[] ids = new String[itemIds.length];
        for (int i = 1; i < itemIds.length; i++) {
            ids[i - 1] = itemIds[i];
        }

        List<EvaluateItem> list = evaluateItemService.list(
                new QueryWrapper<EvaluateItem>().in("item_id", Arrays.asList(ids))
        );
        return Msg.success().add("list", list);
    }
}


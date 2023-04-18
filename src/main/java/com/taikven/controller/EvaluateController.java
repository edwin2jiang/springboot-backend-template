package com.taikven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taikven.dto.PageQueryDto;
import com.taikven.entity.Evaluate;
import com.taikven.entity.Order;
import com.taikven.entity.Result;
import com.taikven.service.EvaluateService;
import com.taikven.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Date: 2023/4/11
 * @Version: 1.0
 * 订单处理
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {
    @Resource
    private EvaluateService evaluateService;

    @Resource
    private OrderService orderService;

    @GetMapping("list")
    @ApiOperation(value = "分页查询")
    public IPage<Evaluate> list(PageQueryDto pageQueryDto) {
        Page<Evaluate> page = new Page<>(pageQueryDto.getPageNum(), pageQueryDto.getPageSize());

        IPage<Evaluate> res = evaluateService.page(page, null);
        return res;
    }

    @GetMapping("")
    @ApiOperation(value = "获取评论信息", notes = "根据评论ID获取评论信息")
    public Result get(Evaluate evaluate) {
        Evaluate ev = evaluateService.getById(evaluate.getEid());
        if (ev != null) {
            return Result.success(ev);
        } else {
            return Result.fail("未找到该评论信息", 500);
        }
    }

    @GetMapping("getByHid/{hid}")
    @ApiOperation(value = "通过hid获取评论信息")
    public Result getByHid(@PathVariable Integer hid) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hid", hid);
        List<Evaluate> list = evaluateService.list(queryWrapper);

        if (list != null) {
            return Result.success(list);
        } else {
            return Result.fail("未找到该评论信息", 500);
        }
    }

    @PostMapping("")
    @ApiOperation(value = "保存评论信息")
    public Result save(@RequestBody Evaluate evaluate) {
        evaluate.setEid(null);
        boolean save = evaluateService.save(evaluate);

        Integer oid = evaluate.getOid();

        if (oid != null) {
            Order order = orderService.getById(oid);
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("oid", oid);

            order.setStatus(3);
            orderService.update(order, queryWrapper);
        }

        return save ? Result.success("保存成功") : Result.fail("保存失败", 500);
    }

    // 删除
    @DeleteMapping("")
    @ApiOperation(value = "删除评论信息")
    public Result delete(@RequestBody Evaluate evaluate) {
        boolean b = evaluateService.removeById(evaluate.getEid());
        return b ? Result.success("删除成功") : Result.fail("删除失败", 500);
    }

    // 修改
    @PutMapping("")
    @ApiOperation(value = "修改评论信息")
    public Result update(@RequestBody Evaluate evaluate) {
        boolean b = evaluateService.updateById(evaluate);
        return b ? Result.success("修改成功") : Result.fail("修改失败", 500);
    }

}

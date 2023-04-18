package com.taikven.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taikven.dto.OrderDateQueryDto;
import com.taikven.dto.OrderQueryDto;
import com.taikven.dto.PageQueryDto;
import com.taikven.entity.Evaluate;
import com.taikven.entity.Order;
import com.taikven.entity.Result;
import com.taikven.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService service;

    @GetMapping("list")
    @ApiOperation(value = "分页查询")
    public IPage<Order> list(PageQueryDto pageQueryDto) {
        Page<Order> page = new Page<>(pageQueryDto.getPageNum(), pageQueryDto.getPageSize());

        IPage<Order> res = service.page(page, null);
        return res;
    }

    @GetMapping("")
    @ApiOperation(value = "获取订单信息", notes = "根据订单ID获取订单信息")
    public Result get(Evaluate evaluate) {
        Order ev = service.getById(evaluate.getOid());
        return ev == null ? Result.fail("未找到该订单信息", 500) : Result.success(ev);
    }

    @GetMapping("getAllOrderByUserId")
    @ApiOperation(value = "根据用户id和民宿id,获取所有订单信息")
    public Result getAllOrderByUserId(OrderQueryDto dto) {
        if (dto.getUid() == null || dto.getHid() == null) {
            // uid或hid为null，返回空结果
            return Result.success(Collections.emptyList());
        }

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", dto.getUid());
        queryWrapper.eq("hid", dto.getHid());

        List<Order> list = service.list(queryWrapper);

        return Result.success(list);
    }


    @GetMapping("getAllOrderByDate")
    @ApiOperation(value = "通过日期, 获取相关订单信息")
    public Result getAllOrderByDate(OrderDateQueryDto dto) throws ParseException {
        if (dto.getUid() == null || dto.getHid() == null) {
            // uid或hid为null，返回空结果
            return Result.success(Collections.emptyList());
        }

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", dto.getUid());
        queryWrapper.eq("hid", dto.getHid());

        // 将字符串的日期转成实际日期
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Date startDate = simpleDateFormat.parse(dto.getStartDate());
        // Date endDate = simpleDateFormat.parse(dto.getEndDate());

        queryWrapper.eq("start_date", dto.getStartDate());
        queryWrapper.eq("end_date", dto.getEndDate());

        List<Order> list = service.list(queryWrapper);

        return Result.success(list);
    }

    @PostMapping("")
    @ApiOperation(value = "保存订单信息")
    public Result save(@RequestBody Order item) {
        item.setOid(null);
        item.setStatus(0);
        boolean save = service.save(item);
        return save ? Result.success("保存成功") : Result.fail("保存失败", 500);
    }

    // 删除
    @DeleteMapping("")
    @ApiOperation(value = "删除订单信息")
    public Result delete(@RequestBody Order item) {
        boolean b = service.removeById(item.getOid());
        return b ? Result.success("删除成功") : Result.fail("删除失败", 500);
    }

    // 修改
    @PutMapping("")
    @ApiOperation(value = "修改订单信息")
    public Result update(@RequestBody Order item) {
        boolean b = service.updateById(item);
        return b ? Result.success("修改成功") : Result.fail("修改失败", 500);
    }

    @PutMapping("{userId}/{orderId}")
    @ApiOperation(value = "订单-确认入住")
    public Result update(@PathVariable Integer userId, @PathVariable Integer orderId) {
        Order order = service.getById(orderId);
        if (order == null) {
            return Result.fail("订单不存在", 500);
        }
        if (order.getUid() != userId) {
            return Result.fail("订单不属于该用户", 500);
        }
        if (order.getStatus() != 0) {
            return Result.fail("订单状态不正确", 500);
        }

        order.setStatus(1);

        boolean b = service.updateById(order);
        return b ? Result.success("修改成功") : Result.fail("修改失败", 500);
    }

}

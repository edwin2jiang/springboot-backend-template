package com.taikven.service.Impl;

import com.taikven.entity.Order;
import com.taikven.mapper.OrderMapper;
import com.taikven.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 
 * @since 2023-04-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}

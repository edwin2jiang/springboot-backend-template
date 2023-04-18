package com.taikven.service.Impl;

import com.taikven.entity.User;
import com.taikven.mapper.UserMapper;
import com.taikven.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

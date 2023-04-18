package com.taikven.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taikven.consts.Code;
import com.taikven.dto.LoginDTO;
import com.taikven.entity.Result;
import com.taikven.entity.User;
import com.taikven.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户添加（用户注册）
     *
     * @param user
     * @return 20011成功 20010失败
     */
    @PostMapping
    @ApiOperation(value = "添加用户（注册）", notes = "用户添加")
    public Result save(@RequestBody User user) {
        boolean flag = userService.save(user);
        return flag ? Result.success("操作成功") : Result.fail("操作失败");
    }

    /**
     * 用户登录
     *
     * @param user
     * @return user
     */
    @PostMapping("login")
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Result login(@RequestBody LoginDTO user) {
        User user1 = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()).eq("password", user.getPassword()));
        if (user1 != null) {
            StpUtil.login(user1.getUid());
            String tokenValue = StpUtil.getTokenValue();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", tokenValue);
            jsonObject.put("user", user1);

            return Result.success(jsonObject);
        }


        return new Result(Code.GET_ERR, "账号或密码错误");
    }


    // 查询登录状态
    @PostMapping("logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Result logout() {
        boolean login = StpUtil.isLogin();
        if (login) {
            StpUtil.logout();
            return Result.success("退出登录成功");
        } else {
            return Result.fail("验证失败, 当前用户未登录");
        }
    }


    // 查询登录状态
    @GetMapping("isLogin")
    @ApiOperation(value = "查询登录状态", notes = "查询登录状态")
    public Result isLogin() {
        return Result.success(StpUtil.isLogin());
    }


}

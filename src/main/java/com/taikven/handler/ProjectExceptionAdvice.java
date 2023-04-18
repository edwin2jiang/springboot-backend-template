package com.taikven.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.taikven.consts.Code;
import com.taikven.entity.Result;
import com.taikven.exception.BusinessException;
import com.taikven.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.taikven.consts.Code.NO_LOGIN_ERROR;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex) {

        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public Result doNotLoginException(NotLoginException ex) {
        return new Result(NO_LOGIN_ERROR, null, ex.getMessage());
    }

    // 处理其他未知异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        ex.printStackTrace();
        System.out.println(ex.getMessage());
        System.out.println("Exception！！");
        return new Result(Code.SYSTEM_UNKNOWN_ERR, null, "系统繁忙，请稍后再试");
    }
}

package com.taikven.entity;

public class Result {


    private Integer code;
    private Object data;
    private String msg;

    public static Result success(Object data) {
        return new Result(200, data, "success");
    }

    public static Result success(Object data, String msg) {
        return new Result(200, data, msg);
    }

    public static Result fail(String msg) {
        return new Result(500, msg);
    }

    public static Result fail(String msg, Integer code) {
        return new Result(code, msg);
    }

    public Result(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

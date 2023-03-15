package com.vdsklnl.common.result;

import lombok.Data;

/**
 * @author vdsklnl
 * @create 2023-03-14 14:36
 * @Description 全局统一返回结果类
 */
//lombok生成对应get、set方法注解
@Data
public class Result<T> {

    private Integer code; //状态码
    private String message; //返回信息
    private T data; //返回数据

    //私有化构造器，无法外部创建对象


    private Result() {
    }

    // 返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    //成功方法
    //第一个T表示T不是类T.class，而是泛型T
    //如果只写List<T>则编译器以为是类T.class，若不存在T.class类报错
    public static <T> Result<T> ok() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    //失败方法
    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    //设置状态码和返回信息
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

}

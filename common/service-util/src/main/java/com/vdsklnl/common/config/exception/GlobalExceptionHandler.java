package com.vdsklnl.common.config.exception;

import com.vdsklnl.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author vdsklnl
 * @create 2023-03-14 18:56
 * @Description
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理执行方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理");
    }

    //特定异常处理(先寻找特定异常处理方法，再寻找全局异常处理方法)
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail().message("执行特定异常处理");
    }

    //自定义异常处理
    @ExceptionHandler(VdsklnlException.class)
    @ResponseBody
    public Result error(VdsklnlException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }
}

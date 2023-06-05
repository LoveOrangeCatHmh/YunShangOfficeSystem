package com.huang.common.config.exceptionhandler;

import com.huang.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GeneralExceptionHandler
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/22  12:00
 * @Version 1.0
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    /**
     * 全局异常处理,执行的方法
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result generalErrorHandler(Exception e){
        //向控制台输出异常信息
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    /**
     * 特定异常处理
     * @param e 算术异常
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result arithmeticExceptionHandler(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("特定异常处理....");

    }

    /**
     * 自定义异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(YunShangException.class)
    @ResponseBody
    public Result yunShangExceptionHandler(YunShangException e){
        e.printStackTrace();
        return Result.fail().code(e.getExceptionCode()).message(e.getExceptionMsg());
    }
}

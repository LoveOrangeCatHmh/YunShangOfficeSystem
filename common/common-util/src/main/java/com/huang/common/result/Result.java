package com.huang.common.result;

import lombok.Data;

/**
 * @ClassName Result
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  16:48
 * @Version 1.0
 * 全局统一结果类,所有接口统一返回结果规范
 */
@Data
public class Result<T> {
    /**
     * 返回请求状态码
     */
    private Integer statusCode;
    /**
     * 返回请求信息
     */
    private String message;
    /**
     * 返回请求数据
     */
    private T data;

    /**
     * 私有化构造器,仅供自己使用,如果暴露使用可以使用静态方法调
     */
    private Result() {

    }

    /**
     * 封装的是返回的数据
     * @param data
     * @param resultCodeEnum
     * @param <T>
     * @return
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        //返回封装的数据
        if (data != null){
            result.setData(data);
        }
        //返回的状态码
        result.setStatusCode(resultCodeEnum.getStatusCode());
        //返回的信息
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 返回成功方法(无数据)统一格式
     */
    public static <T> Result<T> ok() {
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    /**
     * 返回成功方法(有数据)
     */
    public static <T> Result<T> ok(T data) {
        return Result.build(data,ResultCodeEnum.SUCCESS);
    }

    /**
     * 返回失败方法(无数据)
     */
    public static <T> Result<T> fail() {
        return Result.build(null,ResultCodeEnum.FAIL);
    }
    /**
     * 返回失败方法(有数据)
     */
    public static <T> Result<T> fail(T data) {
        return Result.build(data,ResultCodeEnum.FAIL);
    }

    /**
     * 扩展自己想要定义的返回信息
     * @param msg
     * @return
     */
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    /**
     * 扩展自己想要定义的返回状态码
     * @param code
     * @return
     */
    public Result<T> code(Integer code){
        this.setStatusCode(code);
        return this;
    }
}

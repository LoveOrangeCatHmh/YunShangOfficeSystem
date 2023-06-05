package com.huang.common.result;

import lombok.Getter;

/**
 * @ClassName ResultCodeEnum
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  16:41
 * @Version 1.0
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    LOGIN_AUTH(208, "未登录"),
    PERMISSION(209, "没有权限");
    /**
     * 状态码
     */
    private Integer statusCode;
    /**
     * 返回信息
     */
    private String message;

    private ResultCodeEnum(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}

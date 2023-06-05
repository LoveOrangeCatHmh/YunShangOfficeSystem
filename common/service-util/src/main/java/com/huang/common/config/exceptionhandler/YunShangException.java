package com.huang.common.config.exceptionhandler;

import com.huang.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName YunShangException
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/22  14:06
 * @Version 1.0
 * 自定义异常处理
 */
@Data
@ToString
public class YunShangException extends RuntimeException{
    /**
     * 异常状态码
     */
    private Integer exceptionCode;
    /**
     * 异常信息
     */
    private String exceptionMsg;

    public YunShangException(Integer exceptionCode,String exceptionMsg){
        super(exceptionMsg);
        this.exceptionCode = exceptionCode;
        this.exceptionMsg = exceptionMsg;
    }

    /**
     * 接受枚举类型对象
     * @param resultCodeEnum
     */
    public YunShangException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.exceptionCode = resultCodeEnum.getStatusCode();
        this.exceptionMsg = resultCodeEnum.getMessage();
    }
}

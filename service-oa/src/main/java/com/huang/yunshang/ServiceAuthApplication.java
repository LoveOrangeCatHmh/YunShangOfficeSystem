package com.huang.yunshang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ServiceAuthApplication
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  14:05
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.huang")
@MapperScan("com.huang.yunshang.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class,args);
    }
}

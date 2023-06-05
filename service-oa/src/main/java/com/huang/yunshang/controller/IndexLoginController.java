package com.huang.yunshang.controller;

import com.huang.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IndexLoginController
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/23  12:14
 * @Version 1.0
 * 后台登录登出
 */
@Api("后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexLoginController {
    /**
     * login
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(){
        // {"code":200,"data":{"token":"admin-token"}}
        HashMap<String, Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * info
     * @return
     */
    @GetMapping("/info")
    public Result info(){
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    /**
     * logout
     * @return
     */
    @ApiOperation("登出")
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();
    }
}

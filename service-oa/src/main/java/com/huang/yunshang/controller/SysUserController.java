package com.huang.yunshang.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.result.Result;
import com.huang.model.system.SysUser;
import com.huang.vo.system.SysUserQueryVo;
import com.huang.yunshang.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hmh
 * @since 2023-05-24
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("更新用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,@PathVariable Integer status){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }

    @ApiOperation("用户条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result index(@PathVariable Long page, @PathVariable Long limit,SysUserQueryVo userQueryVo){
        //创建Page对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //封装条件,判断条件值不为空
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String username = userQueryVo.getKeyword();
        String createTimeBegin = userQueryVo.getCreateTimeBegin();
        String createTimeEnd = userQueryVo.getCreateTimeEnd();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like(SysUser::getUsername,username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime,createTimeEnd);
        }
        //调用mp方法实现条件分页查询
        Page<SysUser> pageModel = sysUserService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }


    @ApiOperation("根据id查询用户")
    @GetMapping("/get/{userId}")
    public Result queryUserById(@PathVariable Long userId){
        SysUser sysUser = sysUserService.getById(userId);
        return  Result.ok(sysUser);
    }
    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser sysUser){
        sysUserService.save(sysUser);
        return Result.ok();
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result updateByID(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return Result.ok();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/remove/{userId}")
    public Result remove(@PathVariable Long userId){
        sysUserService.removeById(userId);
        return Result.ok();
    }

}


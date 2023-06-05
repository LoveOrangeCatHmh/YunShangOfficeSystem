package com.huang.yunshang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.config.exceptionhandler.YunShangException;
import com.huang.common.result.Result;
import com.huang.common.result.ResultCodeEnum;
import com.huang.model.system.SysRole;
import com.huang.vo.system.AssginRoleVo;
import com.huang.vo.system.SysRoleQueryVo;
import com.huang.yunshang.service.SysRoleService;
import com.huang.yunshang.service.SysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleController
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  16:17
 * @Version 1.0
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService userRoleService;

    //查询所有角色 当前用户所属角色
    @ApiOperation("查询用户角色")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId){
        Map<String, Object> map =  sysRoleService.findRoleDataByUserId(userId);
        return Result.ok(map);
    }

    //2.为用户分配角色
    @ApiOperation("为用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }

    /**
     * 查询所有用户角色
     *
     * @return
     */
    @ApiOperation("查询所有的角色")
    @GetMapping("/findAll")
    public Result findAll() {
        //手动模拟异常效果
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            //抛出自定义异常
            throw new YunShangException(ResultCodeEnum.DATA_ERROR);
        }
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    /**
     * 条件分页查询
     * page 当前页  limit 每页显示记录数
     * SysRoleQueryVo 条件对象
     *
     * @return
     */
    @ApiOperation("条件分页查询角色")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {

        //1.创建Page对象,传递分页相关参数
        Page<SysRole> pageParam = new Page<>(page, limit);
        //2.封装条件,角色名称判断是否为空,不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        //判断条件是否为空,不为空则进行封装
        if (!StringUtils.isEmpty(roleName)) {
            //like代表的是模糊查询 eq代表的是精准匹配
            wrapper.like(SysRole::getRoleName, roleName);
        }
        //3.调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加系统角色")
    @PostMapping("/save")
    public Result save(@RequestBody SysRole role) {
        //调用service方法
        boolean isSuccess = sysRoleService.save(role);
        if (!isSuccess) {
            Result.fail();
        }
        return Result.ok();
    }

    @ApiOperation("根据id查询用户信息")
    @GetMapping("/query/{id}")
    public Result queryById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        if (sysRole != null) {
            return Result.ok(sysRole);
        }
        return Result.ok();
    }

    @ApiOperation("修改角色信息")
    @PutMapping("/update")
    public Result updateSysRole(@RequestBody SysRole role) {
        boolean isSuccess = sysRoleService.updateById(role);
        if (!isSuccess) {
            return Result.fail();
        }
        return Result.ok();
    }

    @ApiOperation("根据id删除角色信息")
    @DeleteMapping("/delete/{id}")
    public Result deleteSysRoleById(@PathVariable Long id) {
        boolean isSuccess = sysRoleService.removeById(id);
        if (!isSuccess) {
            return Result.fail();
        }
        return Result.ok();
    }

    @ApiOperation("根据id批量删除")
    @DeleteMapping("/batchDelete")
    public Result batchDelete(@RequestBody List<Long> ids) {
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if (!isSuccess) {
            return Result.fail();
        }
        return Result.ok();
    }
}

package com.huang.yunshang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.model.system.SysUser;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hmh
 * @since 2023-05-24
 */
public interface SysUserService extends IService<SysUser> {

    //修改用户状态
    void updateStatus(Long id, Integer status);
}

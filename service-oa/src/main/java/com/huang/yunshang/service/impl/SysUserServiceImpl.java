package com.huang.yunshang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.model.system.SysUser;
import com.huang.yunshang.mapper.SysUserMapper;
import com.huang.yunshang.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hmh
 * @since 2023-05-24
 */
@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        //根据userId查询用户对象
        SysUser sysUser = baseMapper.selectById(id);
        //修改用户状态值
        sysUser.setStatus(status);
        //调用方法进行修改
        baseMapper.updateById(sysUser);
    }
}

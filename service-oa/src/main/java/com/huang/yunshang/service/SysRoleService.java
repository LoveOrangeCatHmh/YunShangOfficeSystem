package com.huang.yunshang.service;

import com.huang.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.vo.system.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    /**
     * 查询所有角色 当前用户所属角色
     * @param userId
     * @return
     */
    Map<String, Object> findRoleDataByUserId(Long userId);

    /**
     * 查询所有用户角色
     * @param assginRoleVo
     */
    void doAssign(AssginRoleVo assginRoleVo);
}

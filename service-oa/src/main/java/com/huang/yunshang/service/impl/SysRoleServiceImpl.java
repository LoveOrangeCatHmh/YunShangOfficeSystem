package com.huang.yunshang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huang.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.model.system.SysUserRole;
import com.huang.vo.system.AssginRoleVo;
import com.huang.yunshang.mapper.SysRoleMapper;
import com.huang.yunshang.service.SysRoleService;
import com.huang.yunshang.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  16:03
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleService userRoleService;
    /**
     * 查询所有角色 当前用户所属角色
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {
        //1.查询所有角色,返回list集合, 返回
        List<SysRole> roleList = baseMapper.selectList(null);
        //2.根据userid 查询角色用户关系表,查询出userid对应的所有角色id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList = userRoleService.list(wrapper);
        //3.根据所有的角色id 找到对应的角色对象
        List<Long> existRoleIdList = existUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());
        //根据角色id到所有角色的List集合中进行比较
        List<SysRole> assginRoleList = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            //已分配角色
            if (existRoleIdList.contains(sysRole.getId())){
                assginRoleList.add(sysRole);
            }
        }
        //4.把我们得到的两部分数据封装map集合中,进行返回
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList", assginRoleList);
        roleMap.put("allRolesList", roleList);
        return roleMap;
    }

    /**
     * 重新分配用户角色信息
     * @param assginRoleVo 封装接收前端页面传来的数据
     */
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //把用户之前分配角色数据删除,用户角色关系表里面,根据userid删除
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,assginRoleVo.getUserId());
        userRoleService.remove(wrapper);
        //重新进行分配
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        for (Long roleId : roleIdList) {
            if (StringUtils.isEmpty(roleId)){
                continue;
            }
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            userRoleService.save(sysUserRole);
        }

    }

}

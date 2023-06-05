package com.huang.yunshang.test;

import com.huang.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.yunshang.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SysRoleTest
 * @Description TODO
 * @Author HMH
 * @Date 2023/5/18  14:07
 * @Version 1.0
 */
@SpringBootTest
public class SysRoleTest {
    @Autowired
    private SysRoleMapper mapper;

    @Test
    void getAllSysRole(){
        List<SysRole> roles = mapper.selectList(null);
        roles.forEach(System.out::println);
    }


    @Test
    void add(){
        SysRole role = new SysRole();
        role.setRoleName("贵宾");
        role.setRoleCode("VIP");
        role.setDescription("都是有钱的大冤种");
        int insert = mapper.insert(role);
        System.out.println("role = " + role.getId());
    }

    //@Test
    //void update(){
    //    //1.先查询要修改的数据
    //    SysRole role = mapper.selectById(10);
    //    //2.进行数据修改
    //    role.setDescription("都是有钱的狗东西");
    //    //3.最终修改数据成功
    //    int update = mapper.updateById(role);
    //    System.out.println("role = " + role+"id:"+role.getId());
    //}

    /**
     * 逻辑删除
     */
    @Test
    void deleteBatch(){
        List<Integer> ids = Arrays.asList(1,2,9,10);
        int result = mapper.deleteBatchIds(ids);
        System.out.println("result = " + result);
    }

    /**
     * 条件查询 QueryWrapper
     */
    @Test
    void testQueryWrapper(){
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","贵宾");
        List<SysRole> roleList = mapper.selectList(wrapper);
        roleList.forEach(System.out::println);
    }
    /**
     * 条件查询LambdaQueryWrapper
     */
    @Test
    void testLambdaQueryWrapper(){
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysRole::getRoleName,"普通用户");
        List<SysRole> roleList = mapper.selectList(queryWrapper);
        roleList.forEach(System.out::println);
    }

}

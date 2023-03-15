package com.vdsklnl.auth;

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vdsklnl.auth.mapper.SysRoleMapper;
import com.vdsklnl.auth.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author vdsklnl
 * @create 2023-03-13 21:12
 * @Description
 */
@SpringBootTest
public class TestMapperDemo2 {

    //注入
    @Autowired
    private SysRoleService service;

    //查询所有记录
    @Test
    public void getAll() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        //底层源码中，实际还是调用baseMapper对象进行selectList操作
        List<SysRole> sysRoles = service.list();
        sysRoles.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");

        boolean result = service.save(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole); //id自动回填
    }

    @Test
    public void testUpdateById(){
        SysRole sysRole = new SysRole();
        sysRole.setId(1L);
        sysRole.setRoleName("角色管理员1");

        boolean result = service.updateById(sysRole);
        System.out.println(result);

    }

    @Test
    public void testDeleteById(){
        boolean result = service.removeById(2L);
        System.out.println(result);
    }

    @Test
    public void testSelect1() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("role_code", "role");
        List<SysRole> users = service.list(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void testSelect2() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(SysRole::getRoleCode, "role");
        List<SysRole> users = service.list(queryWrapper);
        System.out.println(users);
    }

}

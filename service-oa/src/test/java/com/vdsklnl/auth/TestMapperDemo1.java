package com.vdsklnl.auth;

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vdsklnl.auth.mapper.SysRoleMapper;
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
public class TestMapperDemo1 {

    //注入
    @Autowired
    private SysRoleMapper mapper;

    //查询所有记录
    @Test
    public void getAll() {
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<SysRole> sysRoles = mapper.selectList(null);
        System.out.println(sysRoles);
    }

    //添加操作
    @Test
    public void add() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员1");
        sysRole.setRoleCode("role1");
        sysRole.setDescription("角色管理员1");
        //返回受影响的行数
        int rows = mapper.insert(sysRole);
        System.out.println(rows);
        System.out.println(sysRole.getId());
    }

    //修改操作
    @Test
    public void update() {
        //根据id查询
        SysRole sysRole = mapper.selectById(10);
        //设置修改值
        sysRole.setRoleName("vdsklnl管理员");
        //调用方法实现最终修改
        int rows = mapper.updateById(sysRole);
        System.out.println(rows);
    }

    //删除操作
    /**
     * application-dev.yml 加入配置
     * 此为默认值，如果你的默认值和mp默认的一样，则不需要该配置
     * mybatis-plus:
     *   global-config:
     *     db-config:
     *       logic-delete-value: 1
     *       logic-not-delete-value: 0
     */
    @Test
    public void delete() {
        int rows = mapper.deleteById(10);
    }

    //批量删除
    @Test
    public void testDeleteBatchIds() {
        //删除id集合(1,2)
        int result = mapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }

    //条件查询
    @Test
    public void testQuery1() {
        //创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        //首参数是表中字段名，非属性名
        queryWrapper.eq("role_name", "总经理");

        //调用mapper方法实现查询操作，仅封装基础查询，多表查询等复杂查询需要编写sql语句
        List<SysRole> sysRoles = mapper.selectList(queryWrapper);
        System.out.println(sysRoles);
    }

    @Test
    public void testQuery2() {
        //创建LambdaQueryWrapper对象，调用方法封装条件
        LambdaQueryWrapper<SysRole> lqw = new LambdaQueryWrapper<>();
        //首参数是直接对应，不用担心写错名称
        lqw.eq(SysRole::getRoleName, "总经理");

        //调用mapper方法实现查询操作
        List<SysRole> sysRoles = mapper.selectList(lqw);
        System.out.println(sysRoles);
    }
}

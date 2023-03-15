package com.vdsklnl.auth.controller;

import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vdsklnl.auth.service.SysRoleService;
import com.vdsklnl.common.config.exception.VdsklnlException;
import com.vdsklnl.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vdsklnl
 * @create 2023-03-14 2:41
 * @Description
 */
@Api(tags = "角色管理接口")
@RestController //Spring管理，返回内存数据
@RequestMapping("/admin/system/sysRole") //Spring管理，返回相关数据
public class SysRoleController {

    //在服务器上启动，路径为：http://localhost:8800/admin/system/sysRole/findAll
    //获得所有查询结果

    //注入Service
    @Autowired
    private SysRoleService service;

//    //查询所有角色
//    @GetMapping("/findAll") //得到结果，方法路径名
//    public List<SysRole> findAll() {
//        //调用service方法
//        List<SysRole> sysRoles = service.list();
//        return sysRoles;
//    }

    //统一返回数据结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll") //得到结果，方法路径名
    public Result findAll() {
        //调用service方法
        List<SysRole> sysRoles = service.list();
        try {
            int i = 1/ 0;
        } catch (Exception e) {
            throw new VdsklnlException(100001, "执行自定义异常处理");
        }
        return Result.ok(sysRoles);
    }

    //条件分页查询
    //page 当前页，limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        //调用service的方法实现
        //1.创建page对象，传递分页相关参数
        Page<SysRole> pageParam = new Page<>(page, limit);
        //2.封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {
            //封装，like表示模糊查询
            wapper.like(SysRole::getRoleName, roleName);
        }
        //3.调用方法实现
        IPage<SysRole> pageModel = service.page(pageParam, wapper);
        return Result.ok(pageModel);
    }

    //添加角色
    //@RequestBody 请求体传递，封装json为对象，GetMapping中没有，更换为PostMapping
    @ApiOperation("添加角色")
    @PostMapping ("save")
    public Result save(@RequestBody SysRole role) {
        //调用service方法
        boolean is_success = service.save(role);
        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    //修改角色-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole sysRole = service.getById(id);
        return Result.ok(sysRole);
    }

    //修改角色
    //@RequestBody 请求体传递，封装json为对象，GetMapping中没有，更换为PostMapping
    @ApiOperation("修改角色")
    @PutMapping ("update")
    public Result update(@RequestBody SysRole role) {
        //调用service方法
        boolean is_success = service.updateById(role);
        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    //根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping ("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = service.removeById(id);
        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

    //批量删除（传入多个id参数）
    //前端传入Json数组[1,2,3]
    @ApiOperation("批量删除")
    @DeleteMapping ("batchRemove")
    public Result remove(@RequestBody List<Long> idList) {
        boolean is_success = service.removeByIds(idList);
        if (is_success)
            return Result.ok();
        else
            return Result.fail();
    }

}

package com.vdsklnl.auth.service.Impl;

import com.atguigu.model.system.SysRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vdsklnl.auth.mapper.SysRoleMapper;
import com.vdsklnl.auth.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author vdsklnl
 * @create 2023-03-14 2:28
 * @Description
 */
//交给Spring进行管理
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {
    //ServiceImpl中完成Mapper和对象注入，不用进行额外声明
    //baseMapper
}

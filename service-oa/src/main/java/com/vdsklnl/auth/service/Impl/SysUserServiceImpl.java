package com.vdsklnl.auth.service.impl;

import com.vdsklnl.auth.entity.SysUser;
import com.vdsklnl.auth.mapper.SysUserMapper;
import com.vdsklnl.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author vdsklnl
 * @since 2023-03-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}

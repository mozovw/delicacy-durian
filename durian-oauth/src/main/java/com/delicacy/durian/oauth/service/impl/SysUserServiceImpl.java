package com.delicacy.durian.oauth.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicacy.durian.oauth.entity.*;
import com.delicacy.durian.oauth.mapper.*;
import com.delicacy.durian.oauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yutao
 * @create 2019-10-22 10:43
 **/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUser getByUsername(String name) {
        SysUserMapper baseMapper = this.getBaseMapper();
        SysUser user = baseMapper.selectOne(Wrappers.<SysUser>query().lambda()
                .eq(SysUser::getUsername, name));
        return user;
    }

    @Override
    public UserInfo userInfoByUsername(String name) {
        UserInfo userInfo = new UserInfo();
        SysUserMapper sysUserMapper = this.getBaseMapper();
        SysUser user = sysUserMapper.selectOne(Wrappers.<SysUser>query().lambda()
                .eq(SysUser::getUsername, name));
        Assert.notNull(user,"user is null");
        userInfo.setSysUser(user);
        // add roles
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(Wrappers.<SysUserRole>query().lambda()
                .eq(SysUserRole::getUserId, user.getUserId()));
        if (ObjectUtils.isEmpty(sysUserRoles)){
            userInfo.setRoles(new String[]{});
            userInfo.setPermissions(new String[]{});
            return userInfo;
        }
        List<SysRole> sysRoles = sysRoleMapper.selectBatchIds(sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
        userInfo.setRoles(sysRoles.stream().map(SysRole::getRoleCode).toArray(String[]::new));
        // addd perms
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(Wrappers.<SysRoleMenu>query().lambda()
                .in(SysRoleMenu::getRoleId, sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList())));
        if (ObjectUtils.isEmpty(sysRoleMenus)){
            userInfo.setPermissions(new String[]{});
            return userInfo;
        }
        List<SysMenu> sysMenus = sysMenuMapper.selectBatchIds(sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList()));
        userInfo.setPermissions(sysMenus.stream().filter(e->!ObjectUtils.isEmpty(e.getPermission())).map(SysMenu::getPermission).toArray(String[]::new));

        return userInfo;
    }
}

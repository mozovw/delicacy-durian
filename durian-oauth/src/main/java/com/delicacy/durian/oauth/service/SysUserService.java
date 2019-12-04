package com.delicacy.durian.oauth.service;


import com.delicacy.durian.oauth.entity.SysUser;
import com.delicacy.durian.oauth.entity.UserInfo;

/**
 * @author yutao
 * @create 2019-10-22 10:41
 **/
public interface SysUserService {
    SysUser getByUsername(String name);
    UserInfo userInfoByUsername(String name);
}


package com.delicacy.durian.oauth.config.oauth;


import com.delicacy.durian.oauth.constants.SecurityConstants;
import com.delicacy.durian.oauth.entity.SysUser;
import com.delicacy.durian.oauth.entity.UserInfo;
import com.delicacy.durian.oauth.service.SysUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DefaultUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService userService;
    @Autowired
    private CacheManager cacheManager;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    @Cacheable(value = SecurityConstants.USER_DETAILS_KEY, key = "#username", unless = "#result == null")
    public UserDetails loadUserByUsername(String username) {
//        Cache cache = cacheManager.getCache("user_details");
//        if (cache != null && cache.get(username) != null) {
//            return (DefaultUser) cache.get(username).get();
//        }
        UserInfo result = userService.userInfoByUsername(username);
        UserDetails userDetails = getUserDetails(result);
//        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 构建userdetails
     *
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(UserInfo result) {
        if (ObjectUtils.isEmpty(result)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result;
        Set<String> dbAuthsSet = new HashSet<>();
        if (!ObjectUtils.isEmpty(info.getRoles())) {
            // 获取角色
            dbAuthsSet.addAll(Arrays.stream(info.getRoles()).collect(Collectors.toList()));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户
        return new DefaultUser(user.getUserId(), user.getDeptId(), user.getUsername(),  user.getPassword(),
                true, true, true, true, authorities);
    }
}

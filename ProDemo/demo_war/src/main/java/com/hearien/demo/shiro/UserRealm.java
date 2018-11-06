package com.hearien.demo.shiro;

import com.google.common.collect.Sets;
import com.hearien.demo.user.model.AppUser;
import com.hearien.demo.user.service.AppUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserRealm
 * @Author WangHaiyang
 * @Date 2018/11/6 12:52
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AppUserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(Sets.newHashSet("admin"));
        authorizationInfo.setStringPermissions(Sets.newHashSet("insert","delete"));
        return authorizationInfo;
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        AppUser user = new AppUser();
        user.setNickname(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(Boolean.TRUE.equals(user.getState())) {
            throw new LockedAccountException(); //帐号锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "admin", //用户名
                "123456", //密码
                ByteSource.Util.bytes("demo"),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}

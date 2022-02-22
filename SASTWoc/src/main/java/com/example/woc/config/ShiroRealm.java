package com.example.woc.config;


import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.service.UserService;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
//  授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        System.out.println("执行授权...");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Account currentaccount = (Account) subject.getPrincipal();
        info.addStringPermission(currentaccount.getRole());
        return info;
    }
//  认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        System.out.println("执行认证...");

        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        Account account = userService.queryAByUname(usertoken.getUsername());
        if(account == null)
        {
            return  null;//抛出UnknownAccountException用户名不存在的异常
        }
        String Salt = account.getUsername();
        return new SimpleAuthenticationInfo(account,account.getPassword(),ByteSource.Util.bytes(Salt), "");
    }
}

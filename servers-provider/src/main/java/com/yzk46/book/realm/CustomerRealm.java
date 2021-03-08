package com.yzk46.book.realm;

import com.yzk46.book.entities.Permission;
import com.yzk46.book.entities.User;
import com.yzk46.book.service.UserService;
import com.yzk46.book.util.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: book
 * @description: 自定义的realm
 * @author: yzk46
 * @create: 2021-03-07 10:47
 **/
public class CustomerRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        String principal = (String) principalCollection.getPrimaryPrincipal();

        //在工厂获取service对象
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");

        User user = userService.getRoleByName(principal);

        if(!ObjectUtils.isEmpty(user)){
            if(!CollectionUtils.isEmpty(user.getRoles())){
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

                user.getRoles().forEach(role -> {
                    authorizationInfo.addRole(role.getName());

                    List<Permission> permList = userService.getPermByname(user.getName());
                    if(!CollectionUtils.isEmpty(permList)){
                        permList.forEach(perms -> {
                            authorizationInfo.addStringPermission(perms.getName());
                        });
                    }

                });
                return authorizationInfo;
            }
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();

        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");

        User user = userService.getUserByName(principal);

        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getName(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        } else {
            System.out.println("获取不到user对象");
        }
        return null;
    }
}

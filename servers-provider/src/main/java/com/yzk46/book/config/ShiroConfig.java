package com.yzk46.book.config;

import com.yzk46.book.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: book
 * @description: shiro配置类
 * @author: yzk46
 * @create: 2021-03-07 10:25
 **/
@Configuration
public class ShiroConfig {

    //1.创建shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //创建一个Map来添加需要鉴权的链接
        Map<String,String> map = new HashMap<>();
        map.put("/shiro/private","authc");//autc,请求这个资源需要认证和鉴权
        map.put("/shiro/register","anon");//anon.匿名过滤器，不需要登录也能访问
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置需要鉴权的链接
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //鉴权失败返回的路径
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //注入realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //3.创建自定义Realm
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();

        //修改校验凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法
        credentialsMatcher.setHashAlgorithmName("Md5");
        //设置迭代次数
        credentialsMatcher.setHashIterations(1024);
        //设置校验凭证匹配器
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}

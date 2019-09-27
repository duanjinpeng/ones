package com.lanxin.shiroconfig;

import com.lanxin.shiro.Demo4;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 86187 on 2019/9/26.
 */
@Configuration
public class ShiroConfig {


    @Value("${spring.redis.host}")
    private  String host;
    @Value("${spring.redis.port}")
    private  int port;
    @Value("${spring.redis.password}")
    private  String password;

@Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(100);
        return  hashedCredentialsMatcher;

    }
@Bean
    public Demo4 demo4(){
        Demo4 demo4 =new Demo4();
        demo4.setCredentialsMatcher(hashedCredentialsMatcher());
        return  demo4;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String,String> map=new HashMap<String ,String >();

        map.put("/login","anon");
        map.put("loginout","anon");
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/unauth");

        return shiroFilterFactoryBean;

    }

    @Bean
    public RedisSessionDAO sessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisma());
        return  redisSessionDAO;
    }


    @Bean
    public DefaultWebSessionManager sessionManager(){

        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(sessionDAO());
        return  defaultWebSessionManager;
    }


    @Bean
    public RedisManager redisma(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPassword(password);
        redisManager.setPort(port);
        return  redisManager;
    }



    //缓存
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager() ;
        redisCacheManager.setRedisManager(redisma());
        return  redisCacheManager;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(demo4());
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;

    }





}
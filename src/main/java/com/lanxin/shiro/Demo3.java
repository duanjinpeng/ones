package com.lanxin.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Created by 86187 on 2019/9/25.
 */

//自定义加密
public class Demo3 {

    public static void main(String[] args) {

        Demo2 demo2=new Demo2();

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(demo2);


        HashedCredentialsMatcher  hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        demo2.setCredentialsMatcher(hashedCredentialsMatcher);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("admin","123123");
        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermissions("save","update");

    }

}

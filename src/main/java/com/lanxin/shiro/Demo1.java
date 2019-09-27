package com.lanxin.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;

/**
 * Created by 86187 on 2019/9/24.
 */
public class Demo1 {



    public static void main(String[] args) {


        DruidDataSource datasource=new DruidDataSource();
        {
            datasource.setUrl("jdbc://localhost:3306/realm");
            datasource.setUsername("root");
            datasource.setPassword("base123");
        }

        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(datasource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken();
        subject.login(usernamePasswordToken);

        System.out.println(subject.isAuthenticated());

        //验证角色
        subject.checkRole("admin");

        //验证权限
        subject.checkPermission("save");
    }
}

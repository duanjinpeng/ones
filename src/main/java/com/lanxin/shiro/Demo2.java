package com.lanxin.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 86187 on 2019/9/24.
 */
//自定义Realm
public class Demo2 extends AuthorizingRealm {

   static    HashMap<String,String> map=new HashMap<>();
    static  HashMap<String,List<String>> listHashMap=new HashMap<>();

    static  {
        map.put("admin","123456");
        map.put("tom","123");

//        listHashMap.put("lili",List<?>);




    }




    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=(String)principalCollection.getPrimaryPrincipal();
        //List<String> roles=getrole

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo();


        return null;

    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username=(String)authenticationToken.getPrincipal();
        String password=username;
        if (password!=null){
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(username,password,"custRealm");
            return  simpleAuthenticationInfo;
        }
        return null;
    }
}

package com.lanxin.shiro;

import com.lanxin.dao.UserDaoImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 86187 on 2019/9/25.
 */

//自定义加盐
public class Demo4 extends AuthorizingRealm {


    @Autowired
    private UserDaoImpl userDao;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo();
        Object obj=(String)principalCollection.getPrimaryPrincipal();
        if(obj instanceof String){

            String username=(String)obj;






        }



        return null;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username=(String)authenticationToken.getPrincipal();
        String password=userDao.selectByusername(username);
      if (password!=null){
          SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo();
          simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("admin"));

          return  simpleAuthenticationInfo;
      }
        return null;
    }
}

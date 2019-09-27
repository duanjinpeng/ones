package com.lanxin.controller;

import com.lanxin.dao.UserDaoImpl;
import com.lanxin.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by 86187 on 2019/9/26.
 */

@RestController
public class UserController {

    @Autowired
    private UserDaoImpl userDao;


    @RequestMapping(value = "/login")
    public Result login(String username,String password){

        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        try {
            subject.login(usernamePasswordToken);

        } catch (IncorrectCredentialsException e) {
            System.out.println("密码输入有误");
            e.printStackTrace();
        }catch (AuthenticationException e2){
            System.out.println("账号输入有误");
        }catch (Exception e3){
            System.out.println("登录失败");
        }

        return  Result.ok();
    }

        //退出
    @RequestMapping(value = "/loginout")
    public  Result loginout(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();

        return  Result.ok("退出成功");
    }


    //退出登录
    @RequestMapping(value = "/add")
    public  Result add(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();

        return  Result.ok("添加成功");
    }

    @RequestMapping(value = "/update")
    public  Result update (){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();

        return  Result.ok("修改成功");
    }

    @RequestMapping(value = "/select")
    public  Result select(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();

        return  Result.ok("查询成功");
    }


    @RequestMapping(value = "/delete")
    public  Result delete(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();

        return  Result.ok("删除成功");
    }



}

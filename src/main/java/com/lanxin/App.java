package com.lanxin;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Md5Hash md5Hash=new Md5Hash("admin","123123");
        System.out.println(md5Hash.toString());

    }
}

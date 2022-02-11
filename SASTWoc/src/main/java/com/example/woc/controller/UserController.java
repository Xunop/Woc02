package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.service.UserService;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //这是一个示例,以POST方法提交数据


    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public void uploadUsername(Account account) {
        userService.uploadUsername(account);
    }

    /**
     * 完成登录功能
     * @param account
     */
    @PostMapping("/login")
    public boolean login(Account account) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(account.getUsername(), account.getPassword());
//        int login = userService.login(account.getUsername(), account.getPassword());
        try{
            subject.login(usernamePasswordToken);
            return true;
        }catch (UnknownAccountException e){
            System.out.println("用户名错误！");
            return false;
        }
        catch (IncorrectCredentialsException e){
            System.out.println("密码错误！");
            return false;
        }
    }
}



package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.entity.Result;
import com.example.woc.mapper.UserMapper;
import com.example.woc.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    //示例
    public void test(String test) {
        userMapper.test(test);
    }
    //注册
    public Result add(Account account){
        Result result = new Result(true, null, null, "注册成功");
        String uname = account.getUsername();
        Account account1 = userMapper.queryByName(uname);
        if (account1 == null){
            //获取密码并进行加密
            String password = account.getPassword();
            //密码进行MD5加密
            MD5 md5 = new MD5();
            String nPwd = md5.md5(password);
            //获取邮箱
            String email = account.getEmail();
            //存入数据库
            userMapper.add(uname,nPwd,email);
        }else {
            result = new Result(false,"注册失败，用户名已存在","403",null);
            LOG.warn( "用户名 " + account1.getUsername() + " 已存在！");
        }
        return result;
    }

    //根据用户名查询用户
    public Account queryByName(String username) {
        // 对参数非空判断
        if (username == null){
            LOG.info("输入的 username 值为空");
            return null;
        }
        return userMapper.queryByName(username);
    }

}

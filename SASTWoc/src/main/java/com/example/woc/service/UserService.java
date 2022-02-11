package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //示例

    //注册

    public int uploadUsername(Account account) {
        String s = userMapper.queryUserByUname(account.getUsername());
        Integer id = userMapper.findId(account.getId());
        if (s == null && id == null){

            //盐
            String Salt = account.getUsername();
            //加密
            SimpleHash simpleHash = new SimpleHash("md5",account.getPassword(),Salt,1);
            String NewPasswoord = simpleHash.toString();

            userMapper.addUser(account.getId(), account.getUsername(), NewPasswoord, account.getEmail(), account.getRole());
            System.out.println("注册成功！");
            return 1;
        }else {
            System.out.println("注册失败！");
            return 0;
        }

    }

//    登录  0为假1为真
//    第一阶段所写，第二阶段直接在controller层写了
//    public int login(String username, String password){
//            String s = userMapper.queryUserByUname(username);
//                if (s == null) {
//                    return 0;
//            }else {
//                    String pword = userMapper.pword(username);
//                    if (SecurityUtils.matchesPassword(password, pword)) {
//                        return 1;
//                    } else {
//                        return 0;
//                    }
//                }
//    }
    public Account queryAByUname(String username){
        return userMapper.queryAByUname(username);
    }
}

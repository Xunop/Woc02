package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.AdminMapper;
import com.example.woc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    //查询用户数
    
    public int queryUserList() {
        int nums = 0;
        List<Account> accounts = adminMapper.queryUserList();
        for (Account account : accounts) {
            nums = nums + 1;
        }
        return nums;
    }

    //查找用户id并删除
    public void deleteAccount(String username) {
        Integer id;
        id = adminMapper.queryUserByUsername(username);
        if(id == null){
            return;
        }
        adminMapper.deleteUser(id);
    }
}

package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public List<Integer> queryId(){
        return adminMapper.queryId();
    }
    public void deleteByName(String username){
        adminMapper.deleteByName(username);
    }
    public void Authorization(String username,Integer role){
        adminMapper.Authorization(username,role);
    }
}

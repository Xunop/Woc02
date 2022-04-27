package com.example.woc.controller;

import com.example.woc.entity.Result;
import com.example.woc.mapper.AdminMapper;
import com.example.woc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService = new AdminService();

    /**
     * 获取当前的账户总数
     * @return Result
     */
    @GetMapping("/getAmount")
    public Result getAmountOfAccounts(){
        Result result = new Result(true, null, null, "账户数目:" + adminService.queryId().size());
        return result;
    }

    /**
     * 根据用户名删除账户
     * @param username
     */
    @PutMapping("deleteAccount")
    public Result deleteAccount(String username){
        adminService.deleteByName(username);
        Result result = new Result(true, null, null, "删除成功");
        return result;
    }

    /**
     * 超级管理员管理用户
     * 给用户授权
     */
    @PutMapping("Authorization")
    public Result Authorization (String username, Integer role){
        if(role == null){
            Result result1 = new Result(false, null, null, "请输入需要授予的角色");
            return result1;
        }
        Result result = new Result(true,null,null,"授权成功！");
        adminService.Authorization(username, role);
        return result;
    }
}

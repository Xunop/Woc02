package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.Result;
import com.example.woc.entity.UserLoginInfo;
import com.example.woc.service.UserService;
import com.example.woc.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    @PostMapping("/simple")
    public void simple(String test) {
        //按住ctrl键来看看具体调用的这个函数吧
        userService.test(test);
    }

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public Result uploadUsername(Account account) {
        return userService.add(account);
    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public Result login(Account account, HttpServletRequest request, HttpServletResponse response) {
        Result loginResult = new Result(true,null,null,"登录成功！");
        // MD5工具
        MD5 md5 = new MD5();
        try {
            //获取数据库中相关用户的信息
            Account account1 = userService.queryByName(account.getUsername());
            String password = account1.getPassword();
            //与密码加密判断是否相等
            String pwdInput = MD5.md5(account.getPassword());
            // 如果密码匹配，存入登录信息(用户名、角色)
            if (password.equals(pwdInput)) {
                // 登录信息
                UserLoginInfo userLoginInfo = new UserLoginInfo();
                // 获取用户的角色
                String role = account1.getRole().toString();
                // 存入信息
                userLoginInfo.setUserRole(role);
                userLoginInfo.setUserName(account.getUsername());
                // 取得 HttpSession 对象
                HttpSession session = request.getSession();
                // 把角色，用户名存入session
                session.setAttribute("userLoginInfo", userLoginInfo);
                return loginResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginResult = new Result(false,"登录失败","403","用户名或密码错误！");
        return loginResult;
    }
}



package com.example.woc.filter;

import com.example.woc.entity.Account;
import com.example.woc.entity.Result;
import com.example.woc.entity.UserLoginInfo;
import com.example.woc.mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Autowired
    private UserMapper userMapper;

    //这个方法写不写都一样
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        javax.servlet.Filter.super.init(filterConfig);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        //统一返回
//        Result result = new Result();
        System.out.println("=====执行=====");
        //基于http请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //请求路径
        String url = request.getRequestURI();

        //鉴权的实现

        //登录跟注册都放行
        if(url.contains("/login") || url.contains("/register")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //拿到session
        HttpSession session = request.getSession();
        //从session中拿到登录信息
        UserLoginInfo userLoginInfo = (UserLoginInfo)session.getAttribute("userLoginInfo");
        //账户没登录
        if(userLoginInfo == null){
            System.out.println("账户未登录");
            Result result = new Result(false, "账号未登录", "400", null);
            //将错误信息封装到request中
            request.setAttribute("error",result);
            //请求转发
            request.getRequestDispatcher("/error").forward(servletRequest,servletResponse);
            return;
        }

        if(url.contains("/admin")){
            //获取用户的role
            String userRole = userLoginInfo.getUserRole();
            //0 普通用户 1 管理员 2 超级管理员
//            if("1".equals(userRole) || "2".equals(userRole)){
//                System.out.println("进入admin");
//                这里这句要是不删的话会放行admin下的所有请求
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
            if (!("1".equals(userRole) || "2".equals(userRole))) {
                //权限不足
                System.out.println("没有权限");
                Result result = new Result(false, "无权限", "401", null);
                request.setAttribute("error", result);
                request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
                return;
            }
        }

        //账户数目 1 2 都能获取,直接放行
        if(url.contains("/getAmount")){
            System.out.println("可以获取权限");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //只有2能删除用户
        if(url.contains("/deleteAccount")){
            String userRole = userLoginInfo.getUserRole();
            if("1".equals(userRole)){
                return;
            }
            //获取需要删除的用户信息
            String username = request.getParameter("username");
            Account account = userMapper.queryByName(username);
            //用户是否存在
            if(account == null){
                System.out.println("需要删除的用户不存在");
                Result result = new Result(false, "需要删除的用户不存在", "400", null);
                request.setAttribute("error", result);
                request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
                return;
            }else {
                //只能删除比他role小的角色
                Integer role = Integer.valueOf(userLoginInfo.getUserRole());
                if(role > account.getRole()){
                    filterChain.doFilter(servletRequest, servletResponse);
                    System.out.println("你的role大你nb，删除成功！");
                    return;
                }else{
                    System.out.println("你的role小，人家的大，你删不了他");
                    Result result = new Result(false, "权限不足，无法删除", "401", null);
                    request.setAttribute("error", result);
                    request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
                    return;
                }
            }
        }

        if(url.contains("/Authorization")){
            //得到需要授权的用户
            String username = request.getParameter("username");
            Account account = userMapper.queryByName(username);
            //用户是否存在
            if(account == null){
                System.out.println("需要授权的用户不存在");
                Result result = new Result(false, "需要授权的用户不存在", "400", null);
                request.setAttribute("error", result);
                request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
                return;
            }else{
                Integer role = Integer.valueOf(userLoginInfo.getUserRole());
                if(role > account.getRole()){
                    System.out.println("可以为他授权");
                    filterChain.doFilter(request,response);
                    return;
                }else{
                    System.out.println("他权限比你还大，你还给人家授权呢");
                    Result result = new Result(false, "权限不足，无法授权", "401", null);
                    request.setAttribute("error", result);
                    request.getRequestDispatcher("/error").forward(servletRequest, servletResponse);
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("清除");
        javax.servlet.Filter.super.destroy();
    }
}

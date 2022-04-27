package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.Result;
import com.example.woc.entity.UserLoginInfo;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class TokenErrorController implements ErrorController {
    private static final String path_default = "/error";

    @RequestMapping(value = path_default,  produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result error(HttpServletRequest request) {
        //从request中取出错误信息
        Result result = (Result) request.getAttribute("error");
        return result;
    }
}

package com.example.woc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 演示用的用户登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo implements Serializable {
    private String userRole;
    private String userName;
}
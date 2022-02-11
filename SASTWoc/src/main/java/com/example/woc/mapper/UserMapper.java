package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    //示例

    String queryUserByUname(String username);//注册时防止重名,查看用户名是否存在
    Account queryAByUname(String username);
    Integer findId(Integer id);
    String pword (String username);//查看用户名对应的密码是否正确
    int addUser(@Param("id") Integer id,@Param("username") String username, @Param("password") String password,@Param("email") String email,@Param("role") String role);
}

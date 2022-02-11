package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    List<Account> queryUserList();//查询用户
    int deleteUser (Integer id);//删
    Integer queryUserByUsername(String username);//根据username查找id
}

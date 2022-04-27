package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    public List<Integer> queryId();
    public void deleteByName(@Param("username") String username);
    public void Authorization(@Param("username") String username, @Param("role") Integer role);
}

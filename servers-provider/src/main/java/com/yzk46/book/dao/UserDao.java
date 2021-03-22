package com.yzk46.book.dao;

import com.yzk46.book.entities.Permission;
import com.yzk46.book.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    int save(User user);

    User getUserByName(String name);

    User getRoleByName(String name);

    List<Permission> getPermByName(String name);

    int updateByName(User user);
}

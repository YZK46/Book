package com.yzk46.book.service;

import com.yzk46.book.entities.Permission;
import com.yzk46.book.entities.User;

import java.util.List;

public interface UserService {
    Integer register(User user);

    User getUserByName(String userName);

    User getRoleByName(String name);

    List<Permission> getPermByname(String name);

    Integer updateByName(User user);
}

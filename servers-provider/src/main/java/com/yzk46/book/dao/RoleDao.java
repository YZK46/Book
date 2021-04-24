package com.yzk46.book.dao;

import com.yzk46.book.entities.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {
    void initRole(UserRole ur);
}

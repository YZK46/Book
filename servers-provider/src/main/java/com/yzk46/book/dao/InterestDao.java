package com.yzk46.book.dao;

import com.yzk46.book.entities.Interest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InterestDao {

    void add(Interest interest);

    Interest query(@Param("id") Integer id);
}

package com.yzk46.book.service;

import com.yzk46.book.entities.Interest;
import org.apache.ibatis.annotations.Param;

public interface InterestService {
    void add(Interest interest);

    Interest query(@Param("id") Integer id);
}

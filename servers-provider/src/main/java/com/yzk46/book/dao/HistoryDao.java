package com.yzk46.book.dao;

import com.yzk46.book.entities.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryDao {

    void addHis(History history);

    List<History> getHis(@Param("uId") Integer uId);
}

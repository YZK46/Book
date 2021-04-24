package com.yzk46.book.dao;

import com.yzk46.book.entities.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordDao {
    void updateRecord(@Param("recordList") List<Record> recordList);
    Record getRecord(@Param("id") Integer id);
}

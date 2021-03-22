package com.yzk46.book.dao;

import com.yzk46.book.entities.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDao {
    Integer save(Tag tag);

    List<Tag> find();

    List<String> findGroup();

    int update(Tag tag);
}

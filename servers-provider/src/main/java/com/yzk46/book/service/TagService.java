package com.yzk46.book.service;

import com.yzk46.book.entities.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagService {
    Integer save(Tag tag);

    List<Tag> find();

    List<String> findGroup();

    int update(Tag tag);
}

package com.yzk46.book.dao;

import com.yzk46.book.entities.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookDao {

    int create(Book book);

    Book getBook(@Param("id") Long id);
}

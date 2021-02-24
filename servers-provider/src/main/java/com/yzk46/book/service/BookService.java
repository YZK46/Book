package com.yzk46.book.service;

import com.yzk46.book.entities.Book;
import org.apache.ibatis.annotations.Param;


public interface BookService {

    int create(Book book);

    Book getBook(@Param("id") Long id);
}

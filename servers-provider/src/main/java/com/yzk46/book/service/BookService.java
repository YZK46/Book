package com.yzk46.book.service;

import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookService {

    int create(Book book);

    Book getBook(@Param("id") Long id);

    List<Book> getBookByTag(Long tagId);

    List<Book> getBookByTitle(String title);

    List<Recommend> getBookForSearch(String title);

    void updateRemark(Book book);

    List<Book> getBookByList(List<Integer> idList);

    void updateRate();

    List<Book> queryByRate();

    List<Book> queryByBrowse();

    List<Book> queryBySearch();

    List<Book> queryByInterest(@Param("id") Integer id);
}

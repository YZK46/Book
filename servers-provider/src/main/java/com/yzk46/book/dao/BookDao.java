package com.yzk46.book.dao;

import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDao {

    int create(Book book);

    Book getBook(@Param("id") Long id);

    List<Book> getBookByTag(Long tagId);

    List<Book> getBookByTitle(String title);

    List<Recommend> getBookForSearch(String title);

    void updateRemark(Book book);

    List<Book> getBookByList(@Param("idList") List<Integer> idList);

    void updateRate(Book book);

    List<Book> queryByRate();

    List<Book> queryByBrowse();

    List<Book> queryBySearch();

    List<Book> queryByInterest(@Param("id") Integer id);
}

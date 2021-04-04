package com.yzk46.book.service.impl;

import com.yzk46.book.dao.BookDao;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.Recommend;
import com.yzk46.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: book
 * @description: 书籍服务实现类
 * @author: yzk46
 * @create: 2021-02-23 22:36
 **/
@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookDao bookDao;

    @Override
    public int create(Book book) {
        return bookDao.create(book);
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getBook(id);
    }

    @Override
    public List<Book> getBookByTag(Long tagId) {
        return bookDao.getBookByTag(tagId);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookDao.getBookByTitle(title);
    }

    @Override
    public List<Recommend> getBookForSearch(String title) {
        return bookDao.getBookForSearch(title);
    }

    @Override
    public void updateRemark(Book book) {
        bookDao.updateRemark(book);
    }
}

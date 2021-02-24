package com.yzk46.book.service.impl;

import com.yzk46.book.dao.BookDao;
import com.yzk46.book.entities.Book;
import com.yzk46.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}

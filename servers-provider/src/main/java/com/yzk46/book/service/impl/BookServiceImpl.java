package com.yzk46.book.service.impl;

import com.yzk46.book.dao.BookDao;
import com.yzk46.book.dao.RecordDao;
import com.yzk46.book.dao.RelateDao;
import com.yzk46.book.entities.Book;
import com.yzk46.book.entities.Recommend;
import com.yzk46.book.entities.Record;
import com.yzk46.book.entities.Relate;
import com.yzk46.book.service.BookService;
import com.yzk46.book.service.RelateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: book
 * @description: 书籍服务实现类
 * @author: yzk46
 * @create: 2021-02-23 22:36
 **/
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookDao bookDao;

    @Resource
    RelateDao relateDao;

    @Resource
    RecordDao recordDao;

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

    @Override
    public List<Book> getBookByList(List<Integer> idList) {
        return bookDao.getBookByList(idList);
    }

    @Override
    public void updateRate() {
        Book book = new Book();
        Relate relate = new Relate();
        for (int i = 0; i < 916 ; i++) {
            relate.setBookId(i);
            List<Relate> relates = relateDao.getRelateByBook(relate);
            Record record = recordDao.getRecord(i);
            if(!CollectionUtils.isEmpty(relates) && record != null){
                Double result = relates.stream().collect(Collectors.averagingDouble(Relate::getRelating));
                BigDecimal b = new BigDecimal((0.8*result*+0.2*(record.getBrowseTimes()+record.getSearchTimes()))*2);
                result = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                book.setId(Long.valueOf(String.valueOf(i)));
                book.setRate(result);
            }else {
                book.setRate(0.0);
                book.setId(Long.valueOf(String.valueOf(i)));
            }
            bookDao.updateRate(book);
        }
    }

    @Override
    public List<Book> queryByRate() {
        return bookDao.queryByRate();
    }

    @Override
    public List<Book> queryByBrowse() {
        return bookDao.queryByBrowse();
    }

    @Override
    public List<Book> queryBySearch() {
        return bookDao.queryBySearch();
    }

    @Override
    public List<Book> queryByInterest(Integer id) {
        return bookDao.queryByInterest(id);
    }
}

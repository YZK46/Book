package com.yzk46.book.service.impl;

import com.yzk46.book.dao.InterestDao;
import com.yzk46.book.entities.Interest;
import com.yzk46.book.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: book
 * @description: 兴趣服务类
 * @author: yzk46
 * @create: 2021-04-15 22:27
 **/
@Service
public class InterestServiceImpl implements InterestService {

    @Resource
    InterestDao interestDao;

    @Override
    public void add(Interest interest) {
        interestDao.add(interest);
    }

    @Override
    public Interest query(Integer id) {
        return interestDao.query(id);
    }
}

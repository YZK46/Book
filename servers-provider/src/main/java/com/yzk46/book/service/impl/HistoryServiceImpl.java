package com.yzk46.book.service.impl;

import com.yzk46.book.dao.HistoryDao;
import com.yzk46.book.entities.History;
import com.yzk46.book.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: book
 * @description: 历史服务
 * @author: yzk46
 * @create: 2021-04-14 21:41
 **/
@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    HistoryDao historyDao;

    @Override
    public void addHis(History history) {
        historyDao.addHis(history);
    }

    @Override
    public List<History> getHis(Integer uId) {
        return historyDao.getHis(uId);
    }
}

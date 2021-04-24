package com.yzk46.book.service.impl;

import com.yzk46.book.dao.RelateDao;
import com.yzk46.book.entities.Relate;
import com.yzk46.book.service.RelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: book
 * @description: 用户书籍关系联系服务
 * @author: yzk46
 * @create: 2021-04-08 13:09
 **/

@Service
public class RelateServiceImpl implements RelateService {

    @Resource
    RelateDao relateDao;

    @Override
    public void updateRelate(Relate relate) {
        relateDao.updateRelate(relate);
    }

    @Override
    public int addRelate(Relate relate) {
        return relateDao.addRelate(relate);
    }

    @Override
    public Relate getRelate(Relate relate) {
        return relateDao.getRelate(relate);
    }

    @Override
    public List<Relate> getRelateByBook(Relate relate) {
        return relateDao.getRelateByBook(relate);
    }

    @Override
    public List<Relate> getAllRelate() {
        return relateDao.getAllRelate();
    }
}

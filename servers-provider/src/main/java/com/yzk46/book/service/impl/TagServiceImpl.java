package com.yzk46.book.service.impl;

import com.yzk46.book.dao.TagDao;
import com.yzk46.book.entities.Tag;
import com.yzk46.book.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: book
 * @description: tag表操作实现
 * @author: yzk46
 * @create: 2021-03-17 22:24
 **/
@Service("TagService")
public class TagServiceImpl implements TagService {

    @Resource
    TagDao tagDao;

    @Override
    public Integer save(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public List<Tag> find() {
        return tagDao.find();
    }

    @Override
    public List<String> findGroup() {
        return tagDao.findGroup();
    }

    @Override
    public int update(Tag tag) {
        return tagDao.update(tag);
    }
}

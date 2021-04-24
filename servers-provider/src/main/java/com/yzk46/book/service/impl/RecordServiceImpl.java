package com.yzk46.book.service.impl;

import com.alibaba.fastjson.JSON;
import com.yzk46.book.constant.RedisCons;
import com.yzk46.book.dao.RecordDao;
import com.yzk46.book.entities.Record;
import com.yzk46.book.service.RecordService;
import com.yzk46.book.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: book
 * @description: 记录服务实现
 * @author: yzk46
 * @create: 2021-04-04 16:51
 **/
@Service
@Slf4j
public class RecordServiceImpl implements RecordService {

    @Resource
    RecordDao recordDao;

    @Resource
    RedisUtil redisUtil;

    @Override
    public void updateRecord() {
        Set<String> keys = redisUtil.getKeys(RedisCons.RECORD_KEY+'*');
        if(CollectionUtils.isEmpty(keys)){
            log.error("methods updateRecord 获取记录的key值为空");
        }else {
            List values = redisUtil.getValuesByKeys(keys);
            if(CollectionUtils.isEmpty(values)){
               log.error("methods updateRecord 获取的值为空");
            }else {
                List<Record> recordList = new ArrayList<>();
                recordList = JSON.parseArray(values.toString(),Record.class);
                recordDao.updateRecord(recordList);
            }
        }

    }

    @Override
    public Record getRecord(Integer id) {
        return recordDao.getRecord(id);
    }
}

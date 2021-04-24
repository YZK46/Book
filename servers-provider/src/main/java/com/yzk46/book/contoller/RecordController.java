package com.yzk46.book.contoller;

import com.alibaba.fastjson.JSON;
import com.yzk46.book.constant.RedisCons;
import com.yzk46.book.constant.ResponseCons;
import com.yzk46.book.entities.CommonResult;
import com.yzk46.book.entities.Record;
import com.yzk46.book.service.RecordService;
import com.yzk46.book.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: book
 * @description: 记录接口
 * @author: yzk46
 * @create: 2021-04-04 17:04
 **/
@RestController
@Slf4j
public class RecordController {

    @Autowired
    RecordService recordService;

    @Resource
    RedisUtil redisUtil;

    @PostMapping("/record/add")
    public CommonResult addRecordTimes(@RequestBody List<Record> recordList){
        CommonResult<Object> commonResult = new CommonResult<>(ResponseCons.FAIL,ResponseCons.ADD_FAIL,null);
        if(!CollectionUtils.isEmpty(recordList)){
            for(Record record: recordList){
                if(!StringUtils.isEmpty(record.getBookId())){
                    try{

                        Record recordCache = JSON.parseObject(redisUtil.getString(RedisCons.RECORD_KEY+record.getBookId().toString()),Record.class);
                        if(recordCache == null){
                            record.setBrowseTimes(Integer.valueOf(RedisCons.BROWSE_INIT));
                            record.setSearchTimes(Integer.valueOf(RedisCons.SEARCH_INIT));
                            redisUtil.setString(RedisCons.RECORD_KEY+record.getBookId().toString(),JSON.toJSONString(record));
                        } else {
                            Integer searchNum = recordCache.getSearchTimes();
                            Integer browseNum = recordCache.getBrowseTimes();
                            if(record.getBrowseTimes() != null && record.getBrowseTimes() == 1){
                                browseNum++;
                            }
                            if(record.getSearchTimes() != null && record.getSearchTimes() == 1){
                                searchNum++;
                            }
                            recordCache.setSearchTimes(searchNum);
                            recordCache.setBrowseTimes(browseNum);
                            redisUtil.setString(RedisCons.RECORD_KEY+record.getBookId(),JSON.toJSONString(recordCache));
                        }
                        commonResult.setResultCode(ResponseCons.SUCCESS);
                        commonResult.setResultMessage(ResponseCons.ADD_SUCCESS);
                    }catch (Exception e){
                        if(log.isErrorEnabled()){
                            log.error("添加搜索记录或浏览记录失败");
                        }
                        record.setBrowseTimes(Integer.valueOf(RedisCons.BROWSE_INIT));
                        record.setSearchTimes(Integer.valueOf(RedisCons.SEARCH_INIT));
                        redisUtil.setString(RedisCons.RECORD_KEY+record.getBookId().toString(),JSON.toJSONString(record));
                        e.printStackTrace();
                    }
                } else {
                    if(log.isErrorEnabled()){
                        log.error("记录类为空,为{}", JSON.toJSONString(record));
                    }
                }
            }
        }
        return commonResult;
    }
}

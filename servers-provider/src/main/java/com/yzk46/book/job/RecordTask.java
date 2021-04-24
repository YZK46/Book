package com.yzk46.book.job;

import com.yzk46.book.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: book
 * @description: 记录类job
 * @author: yzk46
 * @create: 2021-04-05 16:59
 **/
@Slf4j
public class RecordTask implements Job {

    @Autowired
    RecordService recordService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("RecordTask-------- {}", sdf.format(new Date()));

        //将记录数据同步到数据库
        recordService.updateRecord();
    }
}

package com.yzk46.book.job;

import com.yzk46.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: book
 * @description: 定时更新评分
 * @author: yzk46
 * @create: 2021-04-14 12:50
 **/
@Slf4j
public class RateTask extends QuartzJobBean {

    @Autowired
    BookService bookService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("RateTask-------- {}", sdf.format(new Date()));
        //开始更新评分
        bookService.updateRate();
    }
}

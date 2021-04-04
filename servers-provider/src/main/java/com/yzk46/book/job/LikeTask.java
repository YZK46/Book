package com.yzk46.book.job;

import com.yzk46.book.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: book
 * @description: 点赞类JOb
 * @author: yzk46
 * @create: 2021-03-31 21:23
 **/
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    CommentService commentService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LikeTask-------- {}", sdf.format(new Date()));

        //点赞数据同步到数据库中
        commentService.updateLike();
    }
}

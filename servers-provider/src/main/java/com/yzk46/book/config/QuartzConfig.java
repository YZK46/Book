package com.yzk46.book.config;

import com.yzk46.book.constant.QuartzCons;
import com.yzk46.book.job.LikeTask;
import com.yzk46.book.job.RateTask;
import com.yzk46.book.job.RecordTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: book
 * @description: 定时调度工具配置
 * @author: yzk46
 * @create: 2021-03-31 21:22
 **/
@Configuration
public class QuartzConfig    {

    @Bean
    public JobDetail likeDetail(){
        return JobBuilder.newJob(LikeTask.class).withIdentity(QuartzCons.LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public JobDetail recordDetail(){
        return JobBuilder.newJob(RecordTask.class).withIdentity(QuartzCons.RECORD_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public JobDetail rateDetail(){
        return JobBuilder.newJob(RateTask.class).withIdentity(QuartzCons.RATE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger likeTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(1)  //一个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(likeDetail())
                .withIdentity(QuartzCons.LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger recordTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInMinutes(5)  //每5分钟执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(recordDetail())
                .withIdentity(QuartzCons.RECORD_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger ratedTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .withIntervalInHours(24)  //一天执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(rateDetail())
                .withIdentity(QuartzCons.RATE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}

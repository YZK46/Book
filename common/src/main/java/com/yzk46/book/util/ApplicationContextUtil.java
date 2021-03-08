package com.yzk46.book.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: cloud
 * @description: 获取工厂的bean
 * @author: yzk46
 * @create: 2021-03-04 21:40
 **/
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.context = applicationContext;
    }

    //根据bean名字获取工厂中指定bean对象
    public static Object getBean(String name){
        return context.getBean(name);
    }
}

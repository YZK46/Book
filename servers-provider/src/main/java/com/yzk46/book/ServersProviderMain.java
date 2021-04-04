package com.yzk46.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: book
 * @description: 书籍服务启动类
 * @author: yzk46
 * @create: 2021-02-24 15:04
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class ServersProviderMain {
    public static void main(String[] args) {
        SpringApplication.run(ServersProviderMain.class,args);
    }
}

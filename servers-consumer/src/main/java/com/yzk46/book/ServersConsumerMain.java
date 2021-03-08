package com.yzk46.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: book
 * @description: 服务调用启动类
 * @author: yzk46
 * @create: 2021-02-25 11:11
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ServersConsumerMain {
    public static void main(String[] args) {
        SpringApplication.run(ServersConsumerMain.class,args);
    }
}

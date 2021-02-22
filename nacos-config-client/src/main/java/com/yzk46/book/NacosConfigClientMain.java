package com.yzk46.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: book
 * @description: nacos配置中心启动类
 * @author: yzk46
 * @create: 2021-02-22 22:05
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain.class,args);
    }
}

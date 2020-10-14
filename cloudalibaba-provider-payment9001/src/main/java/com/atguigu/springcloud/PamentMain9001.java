package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author:Summer
 * @Date:2020/9/30 15:07
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PamentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PamentMain9001.class,args);
    }
}

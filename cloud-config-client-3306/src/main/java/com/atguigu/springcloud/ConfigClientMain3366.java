package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author:Summer
 * @Date:2020/9/29 14:21
 * @Description:
 */
    @EnableEurekaClient
    @SpringBootApplication
    public class ConfigClientMain3366 {
        public static void main(String[] args) {
            SpringApplication.run(ConfigClientMain3366.class,args);
        }
    }


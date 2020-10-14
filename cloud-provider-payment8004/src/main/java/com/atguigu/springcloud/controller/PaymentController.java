package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

public class PaymentController {
    @Value("${server.port}")
    private  String serverPort;
    @RequestMapping
    public String paymentZk()
    {
        return "springclouf with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}

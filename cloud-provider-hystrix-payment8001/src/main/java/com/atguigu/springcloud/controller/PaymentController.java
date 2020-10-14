package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private  String serverPort;


    //正常访问
    @GetMapping("/payment/hystrix/ok/{id}")
    public  String paymentInfo_ok(@PathVariable("id") Integer id)
    {
        String result=paymentService.paymentInfo_ok(id);
        log.info("reuslt1");
        return  result;
    }

    //错误访问
    @GetMapping("/payment/hystrix/timeout/{id}")
    public  String paymentInfo_timeout(@PathVariable("id") Integer id)
    {
        String result=paymentService.paymentInfo_Timeout(id);
        log.info("reuslt2");
        return  result;

    }

    //服务熔断
    @GetMapping("payment/circuit/{id}")
    public  String paymentCircuitBreaker(@PathVariable("id")Integer id)
    {
        String result=paymentService.paymentCircuitBreaker(id);
        log.info("*****result"+result);
        return result;
    }


}

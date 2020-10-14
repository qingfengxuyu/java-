package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
@RestController
public class OrderConsulController {
    public  static  final  String PAYMENT_URL="http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/consul")
    public String paymentinfo()
    {
        String result=  restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
        return  result;
    }
}

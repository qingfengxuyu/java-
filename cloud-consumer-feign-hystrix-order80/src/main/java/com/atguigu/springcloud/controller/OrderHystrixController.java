package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymrnt_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public  String paymentInfo_ok(@PathVariable("id") Integer id)
    {
        String result=paymentHystrixService.paymentInfo_ok(id);
        return  result;
    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @Hystrix
    public  String paymentInfo_timeout(@PathVariable("id") Integer id)
    {
        String result=paymentHystrixService.paymentInfo_timeout(id);
        return  result;
    }
    public  String paymentInfo_TimeoutHandler(Integer id)
    {
        return " 线程池"+Thread.currentThread().getName()+"消费端80使用降级";
    }
    //下面是全局服务降级处理方法
     public  String paymrnt_Global_FallbackMethod()
     {
         return "Global异常处理信息，请稍后再试";
     }


}

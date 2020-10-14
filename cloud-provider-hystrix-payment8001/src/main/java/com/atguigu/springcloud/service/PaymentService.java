package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    //正常访问
    public  String paymentInfo_ok(Integer id)
    {
        return " 线程池"+Thread.currentThread().getName()+" paymentInfo_OK,id"+id+"\t"+"哈哈";
    }
    //正常访问,设定处理业务的正常时间范围，为3秒，,使用hystrix的线程池的方法,程序出错，服务超时，都会使用服务降级的方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public  String paymentInfo_Timeout(Integer id)
    {
        int timeNumber=3;
        int age=10/0;
        try{TimeUnit.SECONDS.sleep(timeNumber);}
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return " 线程池"+Thread.currentThread().getName()+" paymentInfo_timeout,id"+id+"\t"+"耗时（秒）"+timeNumber;
    }
    public  String paymentInfo_TimeoutHandler(Integer id)
    {
        return " 线程池"+Thread.currentThread().getName()+"使用降级";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
    })

    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id<0)
        {
            throw new RuntimeException("id 不能是负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能是负数，请稍后再试，JI--"+id;

    }

}

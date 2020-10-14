package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private DiscoveryClient discoveryClient;


    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @PostMapping(value = "/payment/create")
    //直接请求服务端可以不用请求体，通过客户端请求(经过两次调用，因此需要用到请求体
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result+"hfdssdfhfgsdfsdf");

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成cx功,serverPort: "+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库xxxx失败,serverport"+serverPort,null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询serverport: "+serverPort,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery()
    {
        List<String> service=discoveryClient.getServices();
        for (String s : service) {
            log.info("内容有哪些"+s);
        }
        List<ServiceInstance>serviceInstances=discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : serviceInstances) {
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }
        return  this.discoveryClient;
    }

}

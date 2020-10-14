package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Summer
 * @Date:2020/10/5 10:38
 * @Description:
 */
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource()
    {
        return new CommonResult(200,"按资源限流测试成功",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException e)
    {
        return new CommonResult(444,e.getClass().getCanonicalName()+"\t 服务不可用");
    }
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value ="cb",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handleException2")
            public CommonResult customerBlockHandle()
    {
        return new CommonResult(200,"安装用户自定义的方法成功",new Payment(200L,"serial003"));
    }

}

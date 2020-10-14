package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Summer
 * @Date:2020/9/27 16:12
 * @Description:
 */
@RefreshScope
@RestController
public class ConfigClientController {
    @Value("${config.info}")//从系统环境配置中获取配置参数
    private String configInfo;
    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }

}

package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author:Summer
 * @Date:2020/9/26 09:45
 * @Description:
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return " PaymentHystrixService 的paymentInfo_ok方法异常";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return " PaymentHystrixService 的paymentInfo_timeout方法异常";
    }
}

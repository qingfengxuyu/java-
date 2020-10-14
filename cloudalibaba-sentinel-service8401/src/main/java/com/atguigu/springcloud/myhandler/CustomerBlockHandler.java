package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

/**
 * @Author:Summer
 * @Date:2020/10/5 11:06
 * @Description:
 */
public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException e)
    {
        return new CommonResult(4444,"按照用户自定义,global-----------------------------1");
    }
    public static CommonResult handleException2(BlockException e)
    {
        return new CommonResult(4444,"按照用户自定义,global-----------------------------2");
    }
}

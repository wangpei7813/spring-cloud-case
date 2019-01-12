package com.wang.userservice.fallback;

import com.wang.order.api.feign.OrderFeignClient;
import org.springframework.stereotype.Component;


@Component
public class OrderFeignClientFallBack extends OrderFeignClient.OrderFeignClientHystrixFallBack {

    @Override
    public String getOrder(String orderId) throws Exception {
        System.err.println("getOrder降级");
        return "getOrder降级";
    }

    @Override
    public String getOrderList() throws Exception {
        System.err.println("getOrderList降级");
        return "getOrderList降级";
    }

}

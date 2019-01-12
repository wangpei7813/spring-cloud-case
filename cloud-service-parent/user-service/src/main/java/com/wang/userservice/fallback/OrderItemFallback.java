package com.wang.userservice.fallback;

import com.wang.order.api.feign.OrderItemFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author wp
 * @date 2019/1/12 10:56
 */
@Component
public class OrderItemFallback extends OrderItemFeignClient.OrderItemFallback {
    @Override
    public String getOrderItemList() throws Exception {
        return "getOrderItemList 降级";
    }
}

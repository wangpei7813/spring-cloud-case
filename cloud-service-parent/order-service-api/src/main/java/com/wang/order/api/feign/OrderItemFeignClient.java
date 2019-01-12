package com.wang.order.api.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author wp
 * @date 2019/1/12 10:52
 */
@FeignClient(name = OrderFeignConstants.FEIGN_NAME, fallback = OrderItemFeignClient.OrderItemFallback.class)
public interface OrderItemFeignClient {

    @GetMapping("/getOrderItemList")
    String getOrderItemList() throws Exception;

    abstract class OrderItemFallback implements OrderItemFeignClient{

    }
}

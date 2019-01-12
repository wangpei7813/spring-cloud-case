package com.wang.order.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Description: @EnableFeignClients 启用内部代理
 * @author: wp
 * @date: 2019/1/12 9:53
 */
@EnableFeignClients("com.wang.order.api.feign")
@EnableCircuitBreaker
@ConditionalOnProperty(prefix="order-service.api.feign", name = {"enabled"}, havingValue="true", matchIfMissing = false)
public class OrderProviderAutoConfiguration {

}

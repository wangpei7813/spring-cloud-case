package com.wang.order.api.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: @FeignClient(name= OrderFeignConstants.FEIGN_NAME, fallback = OrderFeignClient.OrderFeignClientHystrixFallBack.class )
 *              name: 指定代理的服务
 *              fallback：指定短路类
 * @author: wp
 * @date: 2019/1/12 10:39
 */
@FeignClient(name= OrderFeignConstants.FEIGN_NAME, fallback = OrderFeignClient.OrderFeignClientHystrixFallBack.class )
public interface OrderFeignClient {

	@RequestMapping(value="/getOrder")
	String getOrder(@RequestParam("orderId") String orderId) throws Exception;
	
	
	@RequestMapping(value="/getOrderList")
    String getOrderList() throws Exception;
	
	
    abstract class OrderFeignClientHystrixFallBack implements OrderFeignClient {
		
	}
	
	
	
}

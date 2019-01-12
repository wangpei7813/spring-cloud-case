package com.wang.order.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
	
	@GetMapping(value="/getOrder")
	public String getOrder(@RequestParam("orderId")String orderId) throws Exception {
	    log.info("order = {}", orderId);
		return "我是一个订单";
	}
	
	@GetMapping(value="/getOrderList")
	public String getOrderList() throws Exception {
		Thread.sleep(4000);
		return "订单列表";
	}
	
}

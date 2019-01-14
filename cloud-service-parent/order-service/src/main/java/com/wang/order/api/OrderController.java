package com.wang.order.api;

import com.wang.common.constant.Constant;
import com.wang.generator.feign.IdFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private IdFeignClient idFeignClient;
	
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

	@GetMapping("/get-id")
	public Long getId() throws Exception {
	    return idFeignClient.createId();
    }

    @GetMapping(value="/test")
    public Long test() throws Exception {
        return idFeignClient.getId(Constant.ORDER_ID_GENERATOR_KEY);
    }
	
}

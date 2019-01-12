package com.wang.order.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wp
 * @date 2019/1/12 10:50
 */
@RestController
@Slf4j
public class OrderItemController {

    @GetMapping("/getOrderItemList")
    public String getOrderItemList() throws Exception {
        int i = 1 / 0;
        return "getOrderItemList";
    }
}

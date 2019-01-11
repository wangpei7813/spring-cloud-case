package com.wang.couponservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wp
 * @date 2019/1/11 11:19
 */
@RestController
@Slf4j
public class CouponController {

    @GetMapping("/get-coupon-list")
    public String getCouponList() {
        return "获取优惠券列表";
    }
}

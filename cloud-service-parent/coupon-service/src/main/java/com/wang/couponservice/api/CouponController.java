package com.wang.couponservice.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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


    @HystrixCommand(
            commandKey = "getCouponList",
            /*commandProperties = {
                    @HystrixProperty(name = "execution.timeout.enabled",value = "true"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
            },*/
            fallbackMethod = "getCouponListFallBack"
    )
    @GetMapping("/get-coupon-list")
    public String getCouponList() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "获取优惠券列表";
    }

    public String getCouponListFallBack() {
        return "服务超时降级";
    }

    /**
     * @Description: 不涉及远程调用 可以使用 SEMAPHORE 信号量的降级策略
     * @author: wp
     * @date: 2019/1/11 17:25
     */
    @HystrixCommand(
            commandKey = "testSemaphore",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "3")
            },
            fallbackMethod = "testSemaphoreFallBack"
    )
    @GetMapping("test-semaphore")
    public String testSemaphore() {
        return "信号量";
    }


    public String testSemaphoreFallBack() {
        log.info("---------------------------服务信号量降级--------------------------");
        return "服务信号量降级";
    }


}

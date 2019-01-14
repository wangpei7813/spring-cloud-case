package com.wang.generator.feign;

import com.wang.generator.constant.IdFeignConstants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: @FeignClient(name= OrderFeignConstants.FEIGN_NAME, fallback = OrderFeignClient.OrderFeignClientHystrixFallBack.class )
 * name: 指定代理的服务
 * fallback：指定短路类
 * @author: wp
 * @date: 2019/1/12 10:39
 */
@FeignClient(name = IdFeignConstants.FEIGN_NAME, fallback = IdFeignClient.IdFeignClientHystrixFallBack.class)
public interface IdFeignClient {

    @GetMapping("get-id")
    Long getId(@RequestParam("key") String key) throws Exception;

    @GetMapping("/create-id")
    Long createId() throws Exception;


    abstract class IdFeignClientHystrixFallBack implements IdFeignClient {

    }


}

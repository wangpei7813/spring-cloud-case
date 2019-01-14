package com.wang.order.fallback;

import com.wang.common.service.IdGeneratorService;
import com.wang.generator.feign.IdFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author wp
 * @date 2019/1/14 16:18
 */
@Component
public class IdFeignClientFallback extends IdFeignClient.IdFeignClientHystrixFallBack {

    @Override
    public Long getId(String key) throws Exception {
        return IdGeneratorService.createId();
    }

    @Override
    public Long createId() throws Exception {
        return IdGeneratorService.createId();
    }
}

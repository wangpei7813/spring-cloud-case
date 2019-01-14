package com.wang.order.fallback;

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
        return 1234L;
    }
}

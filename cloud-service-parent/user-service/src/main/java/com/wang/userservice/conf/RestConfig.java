package com.wang.userservice.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 *
 * @author wp
 * @date 2019/1/11 11:13
 */
@Configuration
public class RestConfig {

    @Bean
    @ConfigurationProperties(prefix = "custom.requestFactory")
    public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory();
    }

    /**
     * @Description: @LoadBalanced：用于实现负载均衡机制 (根据service-name负载)
     * @author: wp
     * @date: 2019/1/11 11:17
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }
}

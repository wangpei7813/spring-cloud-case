package com.wang.idservice;

import com.wang.idservice.uitl.GuavaUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class IdGeneratorServiceApplication {

    @Bean
    public GuavaUtil guavaUtil() {
        return new GuavaUtil();
    }

	public static void main(String[] args) {
		SpringApplication.run(IdGeneratorServiceApplication.class, args);
	}

}


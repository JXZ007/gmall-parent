package com.atguigu.gmall.web.all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JXZ
 * @create 2021-09-16 11:10
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients({"com.atguigu.gmall.feign"})
public class ServiceWebApplication {
    public static void main(String[] args) {
            SpringApplication.run(ServiceWebApplication.class, args);
        }
}

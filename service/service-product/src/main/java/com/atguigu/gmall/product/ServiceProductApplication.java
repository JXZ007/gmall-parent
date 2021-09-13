package com.atguigu.gmall.product;

import com.atguigu.gmall.common.config.MybatisPlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author JXZ
 * @createDate 2021-09-13 20:33
 */
@SpringCloudApplication
@Import(MybatisPlusConfig.class)
public class ServiceProductApplication {
    public static void main(String[] args) {
            SpringApplication.run(ServiceProductApplication.class, args);
        }
}

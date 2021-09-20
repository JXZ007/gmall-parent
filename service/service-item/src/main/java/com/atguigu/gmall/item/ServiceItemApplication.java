package com.atguigu.gmall.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JXZ
 * @create 2021-09-16 12:46
 */
@EnableFeignClients(basePackages = {
        "com.atguigu.gmall.feign.product"
})  //开启feign远程调用
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ServiceItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceItemApplication.class, args);
    }
    /**
     * 1、如果使用缓存切面。
     * 1）、@Import 把指定组件导入进来，引入第三方
     *
     * 如果标了组件注解的，可以用包扫描机制
     * 2）、@SpringBootApplication(scanBasePackages = {"com.atguigu.gmall.common","com.atguigu.gmall.item"})
     *      只会扫描指定的包下，标了组件注解的组件
     *       （@Controller，@Service，@Component，@Configuration）
     * 3）、@ComponentScan(basePackages = "com.atguigu.gmall.common")
     *      只会扫描指定的包下，标了组件注解的组件
     *
     *  为了告诉Spring，项目启动的时候把哪些组件加载进来，并且默认创建单例对象
     */
    /**
     * 所有和商品有关的数据 由service-product来做的，我们不操作，远程调用
     * 1、feign-client被抽取以后一定用  @EnableFeignClients(basePackages = "com.atguigu.gmall.product")
     * 2、还要加
     * spring:
     *   main:
     *     allow-bean-definition-overriding: true  #允许bean定义信息的重写
     *
     * 以后任意项目起报名
     *      com.atguigu.gmall.模块.mvc三层包
     *
     * 1、SpringBoot 对Redis做了哪些配置
     *      1.1)、RedisAutoConfiguration
     *          RedisTemplate<String, String>  object = 给redis存的是json
     *          StringRedisTemplate:  extends RedisTemplate<String, String>   用这个
     *
     *
     */
}

package com.atguigu.gmall.item.config;

import com.atguigu.gmall.common.config.ItemServiceRedissonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author JXZ
 * @create 2021-09-16 10:55
 */
@Configuration
@Import(ItemServiceRedissonConfig.class)
public class ItemConfig {
}

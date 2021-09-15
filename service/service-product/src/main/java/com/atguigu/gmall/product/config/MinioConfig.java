package com.atguigu.gmall.product.config;

import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author JXZ
 * @create 2021-09-15 19:13
 */
@ConfigurationProperties(prefix = "minio")
@Configuration
@Data
public class MinioConfig {
    private String url;
    private String accessKey;
    private String secretKey;
    private String defaultBucket;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(url, accessKey, secretKey);
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(defaultBucket);
            if (!isExist) {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(defaultBucket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minioClient;
    }
}

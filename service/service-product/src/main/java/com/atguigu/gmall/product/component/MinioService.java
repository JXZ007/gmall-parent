package com.atguigu.gmall.product.component;

import com.atguigu.gmall.product.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author JXZ
 * @create 2021-09-15 19:13
 */
@Component
public class MinioService {
    @Autowired
    MinioClient minioClient;

    @Autowired
    MinioConfig minioConfig;


    public String fileUpload(InputStream inputStream,String bucketName,String fileName) throws Exception {

        if (StringUtils.isEmpty(bucketName)) {
            bucketName = minioConfig.getDefaultBucket();
        }


        // 使用putObject上传一个文件到存储桶中。
        fileName = UUID.randomUUID().toString().replace("-","")+fileName;
        PutObjectOptions putObjectOptions = new PutObjectOptions(inputStream.available(),-1);
        minioClient.putObject(bucketName,fileName, inputStream,putObjectOptions);

        return minioConfig.getUrl()+bucketName+"/" + fileName;

    }
}

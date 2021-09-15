package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.component.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author JXZ
 * @create 2021-09-15 18:51
 */
@RestController
@RequestMapping("/admin/product")
public class FileUploadController {
    @Autowired
    MinioService minioService;

    //http://api.gmall.com/admin/product/fileUpload
    @PostMapping("/fileUpload")
    public Result<String> fileUpload(MultipartFile file)  {
        try {
            InputStream inputStream = file.getInputStream();
            String url = minioService.fileUpload(inputStream, null, file.getOriginalFilename());
            return Result.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("网络异常");
        }

    }
}

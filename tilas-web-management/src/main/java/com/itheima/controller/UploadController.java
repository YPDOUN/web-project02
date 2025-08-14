package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    // 保存在本地的方法
    /*@PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("接收参数：{}", file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //获取文件后缀名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //生成新的文件名
        String newFileName = UUID.randomUUID() + extension;

        //保存文件
        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }*/


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接收参数：{}", file);
        // 将文件上传在阿里云OSS

        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}

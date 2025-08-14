package com.itheima.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint; // OSS访问域名
    private String bucketName; // OSS存储空间名称
    private String region; // OSS地域节点
}

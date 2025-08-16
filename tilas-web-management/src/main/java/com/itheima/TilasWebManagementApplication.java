package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan//开启SpringBoot项目中对servlet组件的支持
@SpringBootApplication
public class TilasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebManagementApplication.class, args);
    }

}

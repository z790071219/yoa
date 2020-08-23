package com.yoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.yoa.dao")
@SpringBootApplication
public class YoaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoaWebApplication.class, args);
    }

}

package com.huaxin.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.huaxin.api.mapper")
@EnableCaching
public class HuaxinApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuaxinApiApplication.class, args);
    }

}

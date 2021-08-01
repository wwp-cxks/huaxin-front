package com.huaxin.api;

import com.huaxin.api.security.filter.JwtAuthenticationTokenFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
/**
 * 指定扫描多个包
 */
@MapperScan(value = {"com.huaxin.api.mapper", "com.huaxin.storage"})
@EnableCaching
public class HuaxinApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuaxinApiApplication.class, args);
    }

    /**
     * 根据SpringBoot官方让重复执行的filter实现一次执行过程的解决方案，
     * 参见官网地址：https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}

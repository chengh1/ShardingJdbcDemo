package com.chengh.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

/**
 * @program: ShardingJdbcDemo
 * @description:
 * @author: chengh
 * @create: 2019-07-01 00:41
 */
@SpringBootApplication(
        scanBasePackages = {"com.chengh.db"},
        exclude = {DruidDataSourceAutoConfigure.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ShardingJdbcAplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcAplication.class, args);
    }

}
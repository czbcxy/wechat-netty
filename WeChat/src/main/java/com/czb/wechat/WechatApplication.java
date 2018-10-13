package com.czb.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper的接口类
@MapperScan(basePackages = "com.czb.wechat.dao")
@ComponentScan(basePackages = {"org.n3r.idworker", "com.czb.wechat"})
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
}

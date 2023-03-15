package com.vdsklnl.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author vdsklnl
 * @create 2023-03-13 21:08
 * @Description
 */
//运行类
@SpringBootApplication
@ComponentScan("com.vdsklnl")
public class ServiceAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

}

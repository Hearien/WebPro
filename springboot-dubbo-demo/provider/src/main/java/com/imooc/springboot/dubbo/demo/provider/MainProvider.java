package com.imooc.springboot.dubbo.demo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class MainProvider {

    public static void main(String[] args) {

        SpringApplication.run(MainProvider.class,args);

    }

}
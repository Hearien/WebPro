package com.hearien.demo;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
@EnableCaching  //开启缓存功能
//@EnableScheduling //开启计划任务支持
//@EnableSwagger2
public class DemoWarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWarApplication.class, args);
	}
}

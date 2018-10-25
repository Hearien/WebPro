package com.songtech.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(value = {"com.songtech.kafkademo"})
public class KafkaDemoApplication extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(KafkaDemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
		logger.info("start successfully");
	}
}

package com.hearien.demo;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class DemoWarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWarApplication.class, args);
	}
}

package com.example.mall.mymall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.mall.mymall.mapper")
public class MyMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMallApplication.class, args);
	}

}

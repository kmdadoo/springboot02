package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication 
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // 이렇게 해야 됨
public class Ex29SecuritytaglibsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex29SecuritytaglibsApplication.class, args);
	}

}

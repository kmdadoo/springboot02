package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication 
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // 이렇게 해야 됨
public class Ex43SecuritySocialLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex43SecuritySocialLoginApplication.class, args);
	}

}

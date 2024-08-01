package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	@SpringBootApplication 어노테이션은 다음과 같이 세 가지 기능이 있다.
	1. @Configuration
		• Bean을 생성할 때 Singletone으로 한 번만 생성
	2. @EnableAutoConfiguration
		스프링 어플리케이션 컨텍스트(Application Context)를 만들 때 자
		동으로 설정하는 기능을 켠다.
	3. @ComponentScan
		지정한 위치 이하에 있는 ©Componenent와 ©Configuration이 붙은 클래
		스를 스캔해서 빈으로 등록
*/
@SpringBootApplication
public class Ex03AnnotationDiApplication {

	public static void main(String[] args) {
		// SpringApplication.run을 통해서 내장 톰캣을 실행한 다음, 자동으로 
		// WebApplicationContext를 생성한다.
		SpringApplication.run(Ex03AnnotationDiApplication.class, args);
	}

}

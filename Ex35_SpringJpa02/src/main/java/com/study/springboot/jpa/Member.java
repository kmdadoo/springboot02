package com.study.springboot.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter	// 클래스 내 모든 필드의 Getter 메소드 자동 생성
@AllArgsConstructor	
@NoArgsConstructor(access = AccessLevel.PROTECTED)	// public Posts(){}와 같은 효과
@Builder	// 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
// entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 
// @Builder를 통해 제공되는 빌더 클래스를 사용
@Entity(name="JPAMEMBER02") 
public class Member
{
	// Entity의 PK는 보통 Long 타입의 Auto_increment를 추천한다. 
	// (이렇게 하면 MySQL 기준 bigint 타입이 됨.)
	@Id	// 해당 테이블의 PK 필드
	@GeneratedValue 	// PK 생성 규칙
	private Long id;	
	private String name;
	private String email;
}
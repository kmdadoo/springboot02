package com.study.springboot.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 롬북을 이용하면 엔티티를 만드는 코드가 아래와 같이 매우 간결해짐.
@Getter	// 케터만 만들겠다.
@AllArgsConstructor	// 여기에 있는 모든 파라미터의 생성자를 만들겠다.
// 생성자를 통해서 값 변경 목적으로 접근하는 메시지들 차단
@NoArgsConstructor(access = AccessLevel.PROTECTED)	// 디폴트 생성자중 PROTECTED로 지정
// 필요한 값만 이용해서 멤버 객체를 생성해 줄수있는 편리한 준 생성자. 
@Builder
@Entity(name="JPAMEMBER01")
public class Member
{
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	@Column(name="create_date")
	private LocalDate createDate;
}
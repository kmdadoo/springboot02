package com.study.springboot.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="JPAMEMBER03")
public class Member
{
	@Id
	@SequenceGenerator( 
			// 우리가 만든 시퀀스를 사용하기 위해 여기서 시퀀스를 만듬
			// 우리가 만든것 과 다르면 에러남.
			name = "mySequence03",
			sequenceName = "JPAMEMBER03_SEQ",
			initialValue = 1,
			allocationSize = 1
	)
	// 여기서 사용한다고 지정
	@GeneratedValue(generator = "mySequence03")
	private Long id;
	private String name;
	private String email;
}

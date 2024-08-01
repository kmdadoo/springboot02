package com.study.springboot;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
	어노테이션을 통한 폼값 검증을 위해서는 추가적인 의존설정(디펜던시)이
	필요하다.
*/
/*
	@JsonProperty : json 형식의 데이터를 받기 위한 어노테이션
	@NotNull : null이 될 수 없는 데이터
	@NotEmpty : 빈 값이 될 수 없는 데이터
	@Min : 상수값 일 때 최소값 지정
	@Size : 문자열에 대한 길이 지정 (message : 조건에 맞지 않을 때의 메시지)
 */
// getter/setter설정을 위해 @Data어노테이션을 부착한다.
@Data
public class ContentDto
{
	private int id;
	
	// 폼값이 null일때 메세지를 출력한다.
	@NotNull(message="writer is null.")
	// 빈값일 때 출력한다.
	@NotEmpty(message="writer is empty.")
	// 입력값의 길이를 최소~최대로 지정한다. 해당범위를 벗어나면 메세지를 출력한다.
	@Size(min=3, max=10, message="writer min 3, max 10.")
	private String writer;
	
	// 널이면 입력된 에러 메시지가 사용
	@NotNull(message="content is null.")
	// 공백이라면 입력된 에러 메시지가 사용
	@NotEmpty(message="content is empty.")
	private String content;
}

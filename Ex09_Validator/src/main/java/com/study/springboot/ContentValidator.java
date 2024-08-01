package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// 폼값 검증을 위해 Validator 인터페이스를 구현한다.
// Validator 구현하기 위해 supports 메서드와 validate 
// 메서드를 반드시 오버라이딩해야 함.
public class ContentValidator implements Validator 
{
	/*
		폼값 검증을 위해 DTO객체를 전달하게되면 해당 메서드가 먼저 호출되어
		전달된 DTO가 검증에 필요한 조건을 가진 커멘드객체인지 검사한다.
		True를 반환하면 validate()메서드가 자동으로 호출되고, 만약 false를
		반환하면 검증이 이루어지지 않는다.
	*/
	@Override
	// Validator가 검증할 수 있는 클래스인지를 판단하는 매서드
	// (작성된 코드 그대로 사용) 여기서는 ContentDto 검증
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0); 
	}

	/*
		폼값 검증을 진행하기 위한 메서드로 여기서는 if문을 통한 검증
		방법을 사용하고 있다.
	*/
	@Override	// 이방법으로 검증을 해라.
	// 데이터의 유효성을 검증하기 위한본인의 코드를 작성
	public void validate(Object obj, Errors errors) {	// 커맨드 객체를 파라미터로 받고
		// Object 파라미터 자리에 원하는 커맨드 객체를 넣고 다시 
		// 형변환해서 사용(‘자식은 부모한테 대입할 수 있다’)
		ContentDto dto = (ContentDto)obj;	// 형변환
		
		// 커맨드 객체로부터 작성자 값을 구해와서 제목을 검증한다. 
		String sWriter = dto.getWriter();
		// 제목을 입력한 값이 null 혹은 빈값인지 확인한다.
		if(sWriter == null || sWriter.trim().isEmpty()) {
			System.out.println("Writer is null or empty");
			/*
				폼값 검증에 오류가 있는 경우 처리 형식
				error객체.rejectValue(폼의 name 속성, 에러객체명, 디폴트메세지)
				다른 곳에서도 이 에러 내용을 이용할 수 있게 된다.
			*/
			errors.rejectValue("writer", "trouble","에러남");
		}
		
		// 커맨드 객체로부터 내용 값을 구해와서 내용을 검증한다.
		String sContent = dto.getContent();
		// 그 값이 널인지 공백인지를 체크하는 로직을 역시 구현
		if(sContent == null || sContent.trim().isEmpty()) {
			System.out.println("Content is null or empty");
			errors.rejectValue("content", "trouble");
		}

	}
}
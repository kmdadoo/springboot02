package com.study.springboot;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

// 폼값 검증을 위해 Validator 인터페이스를 구현한다.
public class ContentValidator implements Validator 
{
	@Override
	public boolean supports(Class<?> arg0) {
		return ContentDto.class.isAssignableFrom(arg0); 
	}

	/*
		폼값 검증을 진행하기 위한 메서드로 여기서는 ValidationUtils 
		클래스를 통한 검증 방법을 사용하고 있다.
	*/
	@Override	// 이방법으로 검증을 해라.
	public void validate(Object obj, Errors errors) {	// 커맨드 객체를 파라미터로 받고
		ContentDto dto = (ContentDto)obj;	// 형변환
		
//		String sWriter = dto.getWriter();
//		if(sWriter == null || sWriter.trim().isEmpty()) {
//			System.out.println("Writer is null or empty");
//			errors.rejectValue("writer", "trouble","에러남");
//		}
		
		/*
			스프링에서 제공하는 유틸 메서드를 사용할 수 있다. 데이터를 
			검증하고 에러가 있을 때는 에러를 처리하는 것까지 이 하나의
			유틸 메서드에서 다 처리하고 있다
		*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "writer is empty.");
		
		String sWriter = dto.getWriter();
		if (sWriter.length() < 3) {
			errors.rejectValue("writer", "writer is too short.");
		}
		
//		String sContent = dto.getContent();
//		if(sContent == null || sContent.trim().isEmpty()) {
//			System.out.println("Content is null or empty");
//			errors.rejectValue("content", "trouble");
//		}
		// 컨텐츠 내용을 비교하는 부분도 동일한 처리
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "content is empty.");
	}
}
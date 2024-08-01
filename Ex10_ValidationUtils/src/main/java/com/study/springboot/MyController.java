package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "ValidationUtils (2)";
    }
    
    // 글쓰기 폼에 대한 매핑
    @RequestMapping("/insertForm")
    public String insert1() {
    	return "createPage";       
    }
     
    // Validator 인터페이스를 통한 폼값 유효성 검증
    @RequestMapping("/create")	// create 가 호출되면
    public String insert2(@ModelAttribute("dto") ContentDto contentDto,
    		              BindingResult result)
    {
    	// 폼값 검증이 완료된 후 포워드할 View의 경로를 설정한다. 
    	String page = "createDonePage";
    	System.out.println(contentDto);
    	
    	ContentValidator validator = new ContentValidator();// 강한 결합
    	validator.validate(contentDto, result);	// 검증
    	
    	// 스프링에서 제공되는 API를 사용하면서 에러를 담은 
		// 결과만 리턴받기 때문에 MyController 클래스에서 에러를 
		// 출력하도록 수정했다.
    	if (result.hasErrors()) {
    		System.out.println("getAllErrors : " + result.getAllErrors());
    		
    		// 제목 검증에 실패한 경우 디폴트 에러코드를 출력한다.
    		if(result.getFieldError("writer") != null) {
    			System.out.println("1:"+result.getFieldError("writer").getCode());
    		}
    		
    		if(result.getFieldError("content") != null) {
    			System.out.println("2:"+result.getFieldError("content").getCode());
    		}
    		page = "createPage"; // 에러가 있으면 이페이지로... 
    	}
    	// 에러가 없다면...
    	return page;       
    }
}
package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "Valid_Annotation (4)";
    }
    
    @RequestMapping("/insertForm")
    public String insert1() {
    	return "createPage";       
    }
     
    @RequestMapping("/create")	// create 가 호출되면
    // 의존성 추가 @Valid 어노테이션 추가
    public String insert2(@ModelAttribute("dto") @Valid ContentDto contentDto,
    		              BindingResult result)
    {
    	String page = "createDonePage";
    	System.out.println(contentDto);
    	
//    	ContentValidator validator = new ContentValidator();// 강한 결합
//    	validator.validate(contentDto, result);	// 검증
    	
    	if (result.hasErrors()) {
    		if(result.getFieldError("writer") != null) {
//    			System.out.println("1:"+result.getFieldError("writer").getCode());
    			System.out.println("1:"+result.getFieldError("writer").getDefaultMessage());
    		}
    		
    		if(result.getFieldError("content") != null) {
//    			System.out.println("2:"+result.getFieldError("content").getCode());
    			System.out.println("2:"+result.getFieldError("content").getDefaultMessage());
    		}
    		page = "createPage"; // 에러가 있으면 이페이지로... 
    	}
    	// 에러가 없다면...
    	return page;       
    }
    
//    @InitBinder	// ContentValidator 대체
//	protected void initBinder(WebDataBinder binder){
//		binder.setValidator(new ContentValidator());
//	}
}
package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "Valid initBinder (3)";
    }
    
    // 글쓰기 폼에 대한 매핑
    @RequestMapping("/insertForm")
    public String insert1() {
    	return "createPage";       
    }
     
    /*
		폼값이 전송되면 ContentDto객체를 통해 한꺼번에 받는다.
		해당 객체에는 폼값 검증을 위한 어노테이션이 추가되어 있으므로
		해당 객체를 통해 검증을 하겠다는 의미로 @Validated어노테이션을 
		추가해야 한다.
	*/
    @RequestMapping("/create")	// create 가 호출되면
    // @Validated  항상 검증하겠다.
    public String insert2(@ModelAttribute("dto") @Validated ContentDto contentDto,
    		              BindingResult result)
    {
    	String page = "createDonePage";
    	System.out.println(contentDto);
    	
//    	ContentValidator validator = new ContentValidator();// 강한 결합
//    	validator.validate(contentDto, result);	// 검증
    	
    	/*
			©Valid 어노테이션으로 contentDto 객체 변수에 대한 유효성 검증을 
			하겠다고 표시를 했고, 파라미터로 객체 변수가 들어오면 스프링이 
			binder 변수에 저장된 객체를 통해서 즉시 유효성 검사를 하고 에러가 
			있다면 result 변수에 담아 둔다
		*/
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
    
    /*
    	 어노테이션을 지정하여 해당 메서드를 프로젝트가 시작할 때 먼저 
    	 실행 시킨다. 그러면 WebDataBinder 타입 변수에 우리가 사용할 
    	 유효성 검증 클래스가 프로젝트가 시작할 때 등록된다.
    */
    @InitBinder	// ContentValidator 대체
	protected void initBinder(WebDataBinder binder){
    	// 개별적으로 생성할 필요 없이 유효성 검증이 필요하면 
    	// binder 변수에서 꺼내서 사용하면 된다.
		binder.setValidator(new ContentValidator());
		/*
			다른 곳에서는 필요할 때마다 매번 new로 만들지 않고 한 번 
			만들어놓은 것을 주입 받아 사용할 수 있게 되었으므로 약한 
			결합으로 사용할 수 있게 된 것이다.
		*/
	}
}
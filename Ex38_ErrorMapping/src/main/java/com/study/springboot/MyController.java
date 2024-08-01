package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "Error Mapping";
	}
	
	@RequestMapping("/test1")
	public String test1() 
	{
		// 없는 페이지 호출
		return "error1";
	}
	
	@RequestMapping("/test2")
	public String test2() 
	{
		// 에러 나는 페이지 호출
		return "error2";
	}
}
package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "NaverEditor 사용하기";
	}
	
	@RequestMapping("/jspeditor")
	public String loginPage(Model model)
	{
		return "editor";
	}
	
	@RequestMapping("/naver_submit")
	public @ResponseBody String myNaverSubmit(HttpServletRequest request) 
	{
		String myVal = request.getParameter("ir1");
		System.out.println("[" + myVal + "]");
		return myVal;
	}
}
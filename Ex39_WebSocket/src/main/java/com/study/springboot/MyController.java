package com.study.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception
	{
		return "WebSocket 사용하기";
	}
	
	@RequestMapping("/login")
	public String loginPage(Model model)
	{
		return "login";
	}
	
	@RequestMapping("/client")
	public String clientPage(Model model)
	{
		return "client";
	}
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter()
	{
		return new ServerEndpointExporter();
	}
}
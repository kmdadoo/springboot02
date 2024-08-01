package com.study.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController
{
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String root() throws Exception
	{
		return "menu";
	}

	@RequestMapping("/insert")
	public String insert(Member member, Model model)
	{
		memberService.insert();
		
		return "insert";
	}
	
	@RequestMapping("/selectAll")
	public String slelectAll(Model model)
	{
		List<Member> result = memberService.selectAll();
		model.addAttribute("members", result);
		
		return "selectAll";
	}
	
	@RequestMapping("/selectById")
	public String selectById(@RequestParam("id") Long search, Model model)
	{
		Optional<Member> result = memberService.selectId(search);
		model.addAttribute("member", result.get());
		
		return "select_id";
	}
	
	// 추가
	@RequestMapping("/selectByName")
	public String selectByName(@RequestParam("name") String search, Model model)
	{
		Optional<Member> result = memberService.selectName(search);
		model.addAttribute("member", result.get());
		
		return "select_name";
	}
	
	@RequestMapping("/selectByEmail")
	public String selectByEmail(@RequestParam("email") String search, Model model)
	{
		Optional<Member> result = memberService.selectEmail(search);
		model.addAttribute("member", result.get());
		
		return "select_email";
	}
	
	@RequestMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String search, Model model)
	{
		String name = search + "%"; // 와일드카드 사용
		List<Member> result = memberService.selectNameLike(name);
		model.addAttribute("members", result);
		
		return "select_name_list";
	}
	
	@RequestMapping("/selectByNameLikeNameDesc")
	public String selectByNameLikeNameDesc(@RequestParam("name") String search, Model model)
	{
		String name = search + "%";
		List<Member> result = memberService.selectNameLikeNameDesc(name);
		model.addAttribute("members", result);
		
		return "select_name_list";
	}

	@RequestMapping("/selectByNameLikeOrder")
	public String selectByNameLikeOrder(@RequestParam("name") String search, Model model)
	{
		String name = search + "%";
		// 객체를 new 할 필요가 없이 사용.
		Sort sort = Sort.by(Sort.Order.desc("name"));
		// 두개를 사용할 때 ,를 사용
//		Sort sort = Sort.by(Sort.Order.desc("name"), Sort.Order.asc("email"));
		
		List<Member> result = memberService.selectNameLike(name, sort);
		model.addAttribute("members", result);
		
		return "select_name_list";
	}
}
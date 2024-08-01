package com.study.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	/*
		url로 selectByNameLike가 리퀘스트 되면 검색어를 파라미터 name을 받음.
		그리고 몇페이지인지 page로 받음
	*/
	@RequestMapping("/selectByNameLike")
	public String selectByNameLike(@RequestParam("name") String search, 
								   @RequestParam("page") String page,
								   Model model)
	{
		System.out.println("***"+ search + "***");
		System.out.println("***"+ page + "***");
		
		String name = search + "%";
		// name으로 오더바이되게 내림차순되게 sort를 만들어 줌.
		Sort sort = Sort.by(Sort.Order.desc("name"));
		// 페이지는 0 부터 처리하기 때문에 1페이지는 0으로 처리함. 2면 1로...
		int nPage = Integer.parseInt(page) - 1;
		
		Pageable pageable = PageRequest.ofSize(10).withPage(nPage).withSort(sort);
		
		Page<Member> result = memberService.selectNameLike(name, pageable);
		List<Member> content = result.getContent();
		long totalElements = result.getTotalElements();
		int  totalPages    = result.getTotalPages();
		int  size          = result.getSize();	// 10개 리스트
		int  pageNumber    = result.getNumber() + 1;	// 0부터 시작하므로
		int  numberOfElements = result.getNumberOfElements();	// content의 갯수
		
		model.addAttribute("members", content);
		model.addAttribute("totalElements", totalElements);
		
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numberOfElements", numberOfElements);
		
		return "select_name_list";
	}
}
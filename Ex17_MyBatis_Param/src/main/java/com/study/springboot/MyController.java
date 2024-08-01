package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

// 껍대기만 있어서 알맹이만 넣어주는 작업만 하면됨. 쉬어짐.
@Controller	
public class MyController 
{
	// Mybatis 사용을 위해 자동 주입
	@Autowired
	ISimpleBbsDao dao;	// 약한 결합으로 주입 받는다.

    @RequestMapping("/")
    public String root() throws Exception{
		return "redirect:list"; // /list로 보냄
    }

    // 게시물 목록
	@RequestMapping("/list")  
    public String userlistPage(Model model) {
		// dao(Mapper)의 listDao() 메서드를 호출해서 리턴값을 
		// model변수에 담음.
		model.addAttribute("list", dao.listDao());
        return "list";
    }

	// 게시물 내용 보기
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		// 개별 게시글을 보기 위해 dao의 viewDao메서드를 호출하여 
		// 리턴값을	model 변수에 담는다.
		model.addAttribute("dto", dao.viewDao(sId));
		return "view";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "writeForm";	// 입력폼을 가진 JSP 파일을 호출
	}
	
	// 게시물 등록
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		// 폼의 입력값을 파라미터로 받이 dao의 writeDao 메서드를 
		// 호출해 데이터베이스에 insert한다.
		dao.writeDao(request.getParameter("writer"),
				     request.getParameter("title"),
				     request.getParameter("content"));
		return "redirect:list";
	}
	
	// 게시물 삭제
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		// 파라미터로 넘어온 값을 이용해 dao의 deleteDao 메서드를 
		// 호출해 데이터베이스에서 게시글을 delete한다.
		dao.deleteDao(request.getParameter("id"));
		return "redirect:list";
	}
}
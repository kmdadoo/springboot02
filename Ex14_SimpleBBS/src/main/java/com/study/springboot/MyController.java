package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

@Controller	
public class MyController 
{
	/*
		DAO의 부모로 선언된 서비스 인터페이스를 통해 dao객채를 생성한다.
		상속구조를 가지고 있으므로 부모를 통해 자식의 메서드를 호출할 수
		있다.
	*/
	@Autowired
	ISimpleBbsDao dao;	// 인터페이스 데이타 사용

    @RequestMapping("/")
    public String root() throws Exception{
        // JdbcTemplate : SimpleBBS
    	// 리다이렉트 기능을 이용해서 url로 /가 호출되면 
    	// url이 자동으로 /list로연결이 되게 만들었다.
		return "redirect:list"; // /list로 보냄
    }

    // 게시물 목록
	@RequestMapping("/list")  // 프론트 컨트롤러
    public String userlistPage(Model model) {
		/*
			DAO 클래스의 select()메서드를 호출하여 회워목록을 List로 반환받은 후
			Model객체에 저장하고 View로 반환 한다.
		*/
		model.addAttribute("list", dao.listDao());
        return "list";
    }

	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		// 개별 게시글을 보기 위해 dao의 viewDao메서드를 호출하여 리턴값을
		// model 변수에 담음.
		model.addAttribute("dto", dao.viewDao(sId));
		return "view";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "writeForm";	// 입력폼 호출
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		// 파라미터가 몇개 안 될때 다 쓰는것이 좋음.
		// 폼의 입력값을 파라미터로 받아 dao의 writeDao 메서드를 
		// 호출해 데이터베이스에 insert를 한다
		dao.writeDao(request.getParameter("writer"),
				     request.getParameter("title"),
				     request.getParameter("content"));
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		// 파라미터로 넘어온 값을 이용해 dao의 deleteDao 메서드를 호출해 
		// 데이터베이스에서 게시글을 delete한다.
		dao.deleteDao(request.getParameter("id"));
		return "redirect:list";
	}
}
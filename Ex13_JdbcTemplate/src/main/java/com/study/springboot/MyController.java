package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.jdbc.MyUserDAO;

@Controller	// 컴포넌트
public class MyController 
{
	// 이 객체 변수에 들어올 수 있는 빈은 앞에서 ©Repository로 등록한
	// 빈, 한 가지밖에 없으니 여기서 자동 주입 이 가능
	@Autowired
	// 오라클 접속 정보로 new 된 것이 들어옴.
    private MyUserDAO userDao;

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception{
        return "JdbcTEmplate 사용하기";
    }

//    @GetMapping("/user")	// 잘 안씀
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userlistPage(Model model) {  // heap에 있는 빈 model
    	// DAO클래스의 list()메서드를 호출하여 회원목록을  userlist로 반환한 후
    	// Model객체에 저장하고 View를 반환한다
		model.addAttribute("users", userDao.list());
        return "userlist";
    }

}
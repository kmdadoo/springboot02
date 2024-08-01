package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.ISimpleBbsDao;
import com.study.springboot.dto.SimpleBbsDto;

/*
	@Controller 로 해도 정상적으로 작동됨. 비즈니스 로직을 담당하는 것을 보여줌.
	빈으로 사용 하겠다는 뜻. 
	@Controller, @Service, @Repository 모두다  @Bean의 스테레오타입이라 함.
	여러가지의 뜻으로 쓰인다는 뜻.
 */
@Service  
public class SimpleBbsService implements ISimpleBbsService {

	@Autowired
	ISimpleBbsDao dao;

	// 원래는 여러개가 있어야함. 지금은 한개만 서비스의 역할은 없음.
	@Override
	public List<SimpleBbsDto> list() {
		return dao.listDao();
	}

	@Override
	public SimpleBbsDto view(String id) {
		return dao.viewDao(id);
	}

	@Override
	public int write(Map<String, String> map) {
		int nResult = dao.writeDao(map);
		return nResult;
	}

	@Override
	public int delete(String id) {
		int nResult = dao.deleteDao(id);
		return nResult;
	}

	@Override
	public int count() {
		int nTotalCount = dao.articleCount();
		return nTotalCount;
	}

}
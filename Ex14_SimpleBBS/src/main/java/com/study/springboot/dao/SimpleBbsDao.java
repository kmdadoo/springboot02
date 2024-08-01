package com.study.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.springboot.dto.SimpleBbsDto;

// @Repository 어노테이션을 통해 스프링컨테이너 시작시 자동으로 빈이 생성된다.
@Repository	// 데이터베이스 처리를 목적으로 하는 빈으로 등록
public class SimpleBbsDao implements ISimpleBbsDao
{
	/*
		인터페이스를 구현한 클래스이므로 정의된 추상메서드를 일과적으로 
		오버라이딩 해야 한다. 따라서 이부분은 자동완성 기능을 사용하면 
		편리하다.
	*/
	
	// JDBC작업을 위해 자동주입 받는다.
	@Autowired
	JdbcTemplate template;
	
	// 게시판 조회
	@Override
	public List<SimpleBbsDto> listDao() {
		System.out.println("listDao()");

		String query = "select * from simple_bbs order by id desc";
		// query 는 데이터가 여러개 있을 때 사용. 
		// 하나일 때는 아래 참조
		List<SimpleBbsDto> dtos = template.query(	
				query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
		
		return dtos;
	}

	// 게시물 조회
	@Override
	public SimpleBbsDto viewDao(String id) {
		System.out.println("viewDao()");
		// 변수(id)를 이용해서 쿼리문 완성
		String query = "select * from simple_bbs where id = " + id;
		/*
			하나의 레코드를 반환하는 select 쿼리문이므로 queryForObject()
			메서드를 사용한다. 두번째 인자를 통해 인출한 레코드를 DTO에 자동으로
			저장해준다. 세번째인자를 통해 쿼리문의 인파리미터를 설정 한다. 이때
			Object형 배열을 사용한다.(예: new Object[] { memberDTO.getId() }))
			여기서는 사용 안됨. 
		*/
		SimpleBbsDto dto = template.queryForObject(
				query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
		return dto;
	}

	//  게시물 등록
	@Override
	public int writeDao(final String writer, final String title, final String content) {
		System.out.println("writeDao()");
		
		/*
			insert, delete, update와 같이 행의 변화가 생기는 쿼리문을 실행할 
			때는 update() 메서드를 사용한다. 실행결과로 변환된 행의 갯수 즉 
			입력된 행의 갯수가 변환되게 된다. 
		*/
		// 인파리미터가 있는 쿼리문 작성
		String query = 
				"insert into simple_bbs (id, writer, title, content) " +
		        " values (simple_bbs_seq.nextval, ?, ?, ?)";
		// update 데이터가 0또는 여러개 있을 때
		return template.update(query, writer, title, content);
	}

	// 게시물 삭제
	@Override
	public int deleteDao(final String id) {
		System.out.println("deleteDao()");
		// 파라미터로 넘어온 값을 이용해 dao의 deleteDao 메서드를 호출해 
		// 데이터베이스에서 게시글을 delete한다.
		String query = "delete from simple_bbs where id = ?";
		return template.update(query, Integer.parseInt(id));
	}
}

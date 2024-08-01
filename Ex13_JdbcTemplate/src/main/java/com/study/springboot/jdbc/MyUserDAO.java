package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
	@Repository 어노테이션은 스테 레오 타입(stereo type)의 일종이다. 
	스테 레오 타입이라는 것은 빈을 등록하고 사용할 때 개발자가 내부
	적으로 의미 파악을 하기 쉽게 하기 위해서 사용하는 별칭이라고 보면 
	된다.
	이 클래스를 빈으로 등록하는데 데이터 베이스와 관련된 처리 용도로 사
	용하겠다는 추가적인 의미를 부여하는 것으로 보면 된다.	
*/
@Repository  // 컨포넌트. 스프링 컨테이너 시작시 자동으로 빈이 둥록
public class MyUserDAO 
{
	//	데이터베이스 사용하기 위한 코드
	// JDBC작업을 위해 자동주입 받는다.
	@Autowired	
	// 자동으로 오라클 안의 객체를 연결. 
	// application.properties의 오라클 정보로 드라이버를 로드하고 데이터
	// 베이스에 접속한 후에 컨넥션 풀까지 생성한 정보가 들어오게 된다.
	private JdbcTemplate jdbcTemplate;
	
	// 회원목록
	public List<MyUserDTO> list() 
	{
		String query = "select * from myuser";	// 결과가 여러개
		
		/*
	      	2개 이상의 레코드를 인출하는 select 쿼리문을 실행할때 
	      	query메서드를 호출한다. 두번째 인수인 RowMapper가 인출된 
	      	레코드를 DTO에 저장한 후 List에 추가하여 반환하게 된다. 
	      	즉  ResultSet을 저장하기 위한 반복적인 작업을 자동으로 
	      	처리해준다. 
	     */
		// 쿼리문 실행
	    List<MyUserDTO> list = jdbcTemplate.query(
	   		query, new BeanPropertyRowMapper<>(MyUserDTO.class));
	    	// MyUserDTO => query의 결과를 넣어줌.
	    
//	    for(MyUserDTO my : list) {	// 디버깅용 나중에 주석
//	     	System.out.println(my);  
//	    }   
	 
	 	return list;
	}
}
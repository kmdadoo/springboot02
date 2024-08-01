package com.study.springboot.dao;

import java.util.List;

import com.study.springboot.dto.SimpleBbsDto;

// 서비스 인터페이스 : 컨트롤러와 모델(DAO) 사이에서 매개역활을 한다.
public interface ISimpleBbsDao
{
	/*
		추상메서드 정의시 매개변수는 일괄적으로 DTO객체를 사용한다.
		커멘드 객체를 사용하면 인수를 한꺼번에 받아 전달할 수 있고, 인수의
		갯수에 변경이 있더라도 DTO객체만 수정하면 되므로 프로그램 작성이
		쉬어진다.
	*/
	// 리스트 보기를 위한 select 메서드를 정의
	public List<SimpleBbsDto> listDao();
	// 개별 뷰 보기를 위한 select 메서드를 정의
	public SimpleBbsDto viewDao(String id);
	// 글 작성을 위한 insert 메서드를 정의
	public int writeDao(String writer, String title, String content);
	// 글 삭제를 위한 delete 메서드를 정의
	public int deleteDao(String id);
}

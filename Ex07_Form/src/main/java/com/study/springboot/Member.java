package com.study.springboot;

/*
	이런 형태의 객체를 ‘커맨드(Command) 객체'라고 부른다.
	데이터베이스 테이블과 관련해서 이야기할 때는 DTO, 지금처럼
	파라미터와 관련해서 이야기할 때는 커맨드 객체라고 하면 된다.
*/
public class Member
{
	private String id;
	private String name;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}

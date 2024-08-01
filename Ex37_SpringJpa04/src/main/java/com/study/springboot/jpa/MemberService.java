package com.study.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService
{
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> selectMembers1(String search)
	{
		List<Member> member = memberRepository.findMembers(search);
		return member;
	}

	public List<Member> selectMembers2(String search, Sort sort)
	{
		List<Member> member = memberRepository.findMembers(search, sort);
		return member;
	}
	
	public Page<Member> selectMembers3(String search, Pageable pageable)
	{
		Page<Member> member = memberRepository.findMembers(search, pageable);
		return member;
	}
	
	public List<Member> selectMembers4(String search)
	{
		List<Member> member = memberRepository.findMembersNative(search);
		return member;
	}
	
	// 리턴하는 것이 List<Member>와 Page<Member> 두개가 있다.
}
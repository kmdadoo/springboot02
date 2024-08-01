package com.study.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//MemberRepository를 사용하는 서비스
@Service
public class MemberService
{
	@Autowired
	private MemberRepository memberRepository;
	
	public Member insert(Member member)
	{
		// MemberRepository에 메서드가 없지만 자동으로 생성되어 
		// save, findById 등등을 사용할 수 있음.
		Member returnMember = memberRepository.save(member);
		return returnMember;
	}
	
	public Optional<Member> select(Long id)
	{
		// findById 이것은 Member의 @Id를 말함.
		Optional<Member> member = memberRepository.findById(id);
		return member;
	}

	public List<Member> selectAll()
	{
		return memberRepository.findAll();
	}

	public void delete(Long id)
	{
		memberRepository.deleteById(id);
	}

	public Member update(Member member)
	{
		// insert 메서드에서 사용한 방법과 똑같다.
		// 키 값과 같은 값이 있으면 업데이트, 없으면 인서트
		Member returnMember = memberRepository.save(member);
		return returnMember;
	}
}
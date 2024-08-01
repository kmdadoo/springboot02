package com.study.springboot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
	// 제네릭 타입 : long이 아니라 Long으로 작성
	// 기본적인 Create, Read, Update, Delete 자동으로 생성
}
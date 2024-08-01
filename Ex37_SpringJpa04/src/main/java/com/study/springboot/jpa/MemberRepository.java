package com.study.springboot.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
	/*
		 일반 JPQL쿼리, from뒤는 엔티티 명 (소문자로 할 시 에러)
		 JPAMEMBER03은 데이터베이스에 있는 테이블명이 아니고 우리 영속성 객체에
		 있는 엔티티 명입니다. 그래서 이렇게 JPQL쿼리는 영속성 객체를 대상으로 
		 쿼리문을 갖다 만드므로 바로 사용하면 안되고 알리아스를 만들어서 사용해야함.
		 :name1은 아래 @Param에 있는 name1을 사용.
	*/
	@Query("select m from JPAMEMBER03 m where m.name like :name1 order by m.id desc")
	List<Member> findMembers(@Param("name1") String name2);

	@Query("select m from JPAMEMBER03 m where m.name like :name1")
	List<Member> findMembers(@Param("name1") String name2, Sort sort);
	
	@Query("select m from JPAMEMBER03 m where m.name like :name1")
	Page<Member> findMembers(@Param("name1") String name2, Pageable pageable);
	
	// 일반 SQL쿼리 : 테이블명 등 대소문자 가리지 않는다.
	// 			value와 nativeQuery를 명시해서 사용하면 일반 
	//          SQL문을 그대로 사용할수 있다.
	@Query(value = "select * from jpamember03 where name like :name1 order by id desc",
			nativeQuery = true)
	List<Member> findMembersNative(@Param("name1") String name2);
}
package com.study.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
	// 제네릭 타입 : long이 아니라 Long으로 작성
	// 기본적인 Create, Read, Update, Delete 자동으로 생성
	
	// findBy 뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다.
	Optional<Member> findByName(String keyword);
	Optional<Member> findByEmail(String keyword);
	
	// 다양한 확장이 가능하다. 컬럼명으로 지정.
	List<Member> findByNameLike(String keyword);	// like조건으로
	// 정렬조건으로 name을 내림차순으로
	List<Member> findByNameLikeOrderByNameDesc(String keyword);	
	// like로 검색조건을, 이름은 오름차순으로 이메일은 내림차순으로
	List<Member> findByNameLikeOrderByNameAscEmailDesc(String keyword);
	
	// 오름차순까지 넣으면 너무길어지므로 Sort 객체로 오더바이를 지정
	List<Member> findByNameLike(String keyword, Sort sort);
	
	// 우리는 이렇게 메서드만 정의를 해놨지만 스프링데이터JPA가 자동으로
	// 메서드를 전부다 만들어 줌.
	// 이렇게 JPA레파이토리를 이용하면 아주 간단하게 jpa의 기능을 갖다가
	// 구현하고 사용할수 있다.
}
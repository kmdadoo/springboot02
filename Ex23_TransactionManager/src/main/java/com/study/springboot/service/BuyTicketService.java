package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService 
{
	// Jdbc 연동을 위한 자동주입
	@Autowired // 자동 주입을 받아 변수를 만든다.
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;
	
	// 트랜잭션 처리를 위한 자동 주입
	@Autowired	// 스프링안에 기본적으로 제공하는 것.
	PlatformTransactionManager transactionManager;
	@Autowired
	// 트랜잭션 매니저에서 사용할 설정을 만드는 데 설정값은 기본으로 
	// 제공되는 값을 변경 없이 그대로 사용
	TransactionDefinition definition;  // definition : 기본 설정 값을 사용하겠다는 것.

	@Override
	public int buy(String consumerId, int amount, String error) 
	{
		// 트랜잭션 설정. 이 설정 이후의 데이터베이스 처리에 대해서 
		// 트랜잭션을 처리할 수 있게 된다
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			// 여기로 롤백을 함.
			transaction1.pay(consumerId, amount); // 현장 판매처 상황
			
			// 의도적 에러 발생
			if (error.equals("1")) { int n = 10 / 0;}
			transaction2.pay(consumerId, amount);	// 회계장부 상황
			
			// 트랜잭션에 대한 커밋 처리를 해주고 있다. 이전 설정부터 
			// 현재 라인까지의 데이터베이스처리 결과에 대해서 커밋한다.
			transactionManager.commit(status);
			return 1;
		} catch(Exception e) {
			System.out.println("[PlatformTransactionManager] Rollback");
			// 에러가 나면 transaction1안에 들어와 있던 데이터도 
			// 롤벡이 되어서 없어짐.
			transactionManager.rollback(status);
			return 0;
		}
	}
}
package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService 
{
	// Jdbc 연동을 위한 자동주입
	@Autowired 
	ITransaction1Dao transaction1;
	@Autowired
	ITransaction2Dao transaction2;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	/*
		전파 속성
		REQUIRES_NEW : 각각의 트랜잭션을 처리한다. 즉 포함시킨 메서드에서
			에러가 발생하더라도 포함된 메서드에서는 정상처리된다.
		REQUIRED : 기존 트랙잰션에 의존한다. 즉 포함된 메서드나 포함시킨
			메서드 어느쪽이든 오류가 발생하면 모든 작업이 rollback 된다.
			별도의 설정이 없으면 해당 값이 디폴트로 지정된다.
	*/
	// 1,2 와 3은 별도의 트렌잭션으로 처리한다. 1,2는 입력되나 3이 입력이 안된다.
//	@Transactional(propagation=Propagation.REQUIRES_NEW) 
	// 전체 처리가 된다.
	@Transactional(propagation=Propagation.REQUIRED)
	
	@Override
	public int buy(String consumerId, int amount, String error) 
	{
		try {	
			transactionTemplate.execute(new TransactionCallbackWithoutResult() 
			{  
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg0) 
				{
					transaction1.pay(consumerId, amount); // 현장 판매처 상황
			
					// 의도적 에러 발생
					if (error.equals("1")) { int n = 10 / 0;}
					transaction2.pay(consumerId, amount);	// 회계장부 상황	
				}	// 여기가 그림에서의 트랜잭션 B
			});
			return 1;
		} catch(Exception e) {
			System.out.println("[PlatformTransactionManager #2] Rollback");
			return 0;
		}
	}
}
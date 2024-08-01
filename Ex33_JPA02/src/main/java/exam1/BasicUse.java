package exam1;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class BasicUse 
{
	public static void main(String[] args) 
	{
		// 그대로 사용하면 됨.
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("JpaEx01");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            
            Member1 user = new Member1("홍길동1", LocalDate.now());
            entityManager.persist(user);	// 영속성으로 insert해준것
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
        	entityManager.close();
        }

        emf.close();
	}
}

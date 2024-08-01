package exam4;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Test02_TypedQuery
{
	public static void main(String[] args)
	{
		// hibernate.hbm2ddl.auto의 value : none, create
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("JpaEx01");
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            
            // SQL문을 직접 작성
            TypedQuery<Member4> query =
                    em.createQuery(
                    		// 테이블에 알리어스를 사용
                            "select m from Member4 m order by m.name",
                            Member4.class);
            List<Member4> result = query.getResultList();
            
            em.getTransaction().commit();

            if (result.isEmpty()) 
            {
                System.out.println("사용자가 없습니다.");
            }
            else 
            {
            	result.forEach(user ->	// 람다식을 이용
                        System.out.printf(
                                "| %s | %s | %tY-%<tm-%<td |\n",
                                user.getEmail(), user.getName(), user.getCreateDate()));
            }
        
        } catch(Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        em.close();
        emf.close();
	}
}

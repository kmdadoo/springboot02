package exam3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test04_Delete
{
	public static void main(String[] args)
	{
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("JpaEx01");
        EntityManager em = emf.createEntityManager();

        try 
        {
            em.getTransaction().begin();
            
            // 먼저 객체를 찾음.
            Member3 user = em.find(Member3.class, "test@test.com");
            if (user == null) {
                System.out.println("존재하지 않습니다.");
                em.getTransaction().rollback();
                return;
            }
            em.remove(user);	//  찾았으면 지움.
        
            em.getTransaction().commit();	// 동기화
            System.out.println("탈퇴처리 했습니다.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }

        em.close();
        emf.close();
	}
}
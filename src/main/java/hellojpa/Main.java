package hellojpa;

import hellojpa.entity.*;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setEmail("lds2292@naver.com");
            member.setName("이덕수");
            em.persist(member);

            Seller seller = new Seller();
            seller.setName("대표자");
            seller.setShopName("카카오프렌즈");
            em.persist(seller);

            em.flush();
            em.clear();

            Seller findSeller = em.find(Seller.class, seller.getId());
            System.out.println(findSeller.getShopName());

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

        emf.close();
    }
}

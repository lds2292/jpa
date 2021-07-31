package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

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
            Team team = new Team();
            team.setId(1L);
            team.setName("카카오프렌즈");

            Member member1 = new Member();
            member1.setId(1L);
            member1.setUsername("라이언");

            Member member2 = new Member();
            member2.setId(2L);
            member2.setUsername("어피치");
            member2.setTeam(team);

            Member member3 = new Member();
            member3.setId(3L);
            member3.setUsername("춘식이");

            em.persist(team);
            em.persist(member2);
            em.persist(member1);
            em.persist(member3);

            member1.setTeam(team);
            member3.setTeam(team);

            em.flush();
            em.clear();

            System.out.println("----------");

            List<Member> members = em.find(Team.class, 1L).getMembers();
            for (Member member : members) {
                System.out.println("member.username() = " + member.getUsername());
            }
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

        emf.close();
    }
}

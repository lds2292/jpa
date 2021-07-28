package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team1 = new Team();
            team1.setName("카카오프렌즈");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("라인프렌즈");
            em.persist(team2);

            // 사용자 생성
            Member member = new Member();
            member.setId(1L);
            member.setName("라이언");
            member.setTeam(team1);
            em.persist(member);

            // 팀수정
            member.setTeam(team2);

            // Member @OneToMany의 fetchType.LAZY로 인하여 멤버만 조회됨
            Member findMember = em.find(Member.class, 1L);
            // getTeam의 참조로 인하여 이시점에 Team을 조회
            Team findTeam = findMember.getTeam();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");

        testSave(emf);

        emf.close();
    }

    // 예제 5.6
    public static void testSave(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        Team team1 = new Team("카카오프렌즈");
        em.persist(team1);

        Member member1 = new Member("라이언");
        em.persist(member1);

        Member member2 = new Member("어피치");
        member2.setTeam(team1);
        em.persist(member2);

        member1.setTeam(team1);

        // 쿼리로 멤버 조회(팀이름으로)
        queryLogicJoin(em);
        // 업데이트
        updateRelation(em);
        // 제거
        deleteRelation(em);
        // 팀제거 (team1을 제거하기 위해 기존 연결된 관계를 제거해야한다 안그러면 외래키 제약조건에 의해 오류가남
        member2.setTeam(null);
        em.remove(team1);

        // 저장
        tx.commit();
//        // 멤버 조회
//        // 트랜잭션 커밋 이후에 조회를 하면 영속성 컨텍스트에서 가져올지? 아니면 디비에서 조회할지?
//        // 조회시 이미 영속성 컨텍스트에 있기 때문에 디비조회 없이 가져온다
//        System.out.println(">>>>조회");
//        Member findMember = em.find(Member.class, 1L);
//        Member findMember2 = em.find(Member.class, 1L);
//        System.out.println(">>>>"+findMember.getName());
//        System.out.println("findMember == findMember2 비교 / "+(findMember == findMember2));
//
//        // 엔티티매니저를 비우고 다시 시도
//        System.out.println("엔티티매니저 비우기");
//        em.clear();
//        Member findMember3 = em.find(Member.class, 1L);
//        // 디비에서 조회해 온다
//        System.out.println(findMember3.getName());
//
//        // findMember와 findMember2는 동일한가?
//        System.out.println(findMember == findMember3); // 다르다
    }

    public static void queryLogicJoin(EntityManager em){
        String jpql = "select m from Member m join m.team t where t.name = :teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class).setParameter("teamName", "카카오프렌즈").getResultList();
        for (Member member : resultList) {
            System.out.println("[query] member.username = "+member.getName());
        }
    }

    public static void updateRelation(EntityManager em){
        Team team2 = new Team("라인프렌즈");
        em.persist(team2);

        Member member = em.find(Member.class, 1L);
        member.setTeam(team2);
    }

    public static void deleteRelation(EntityManager em){
        Member member = em.find(Member.class, 1L);
        member.setTeam(null);
    }
}

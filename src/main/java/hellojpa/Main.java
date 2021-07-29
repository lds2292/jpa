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
            // 팀생성
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
            em.persist(member);

            // 양방향 관계 멤버(Owner) <-> 팀
            // 1.라인프렌즈 팀에서 라이언을 추가하면 어떻게 될까?
            // 팀에 라이언을 추가했으니 라이언이 추가됐을거라고 보겠지만 결과는 TEAM_ID = NULL 로 들어갔다.
            // 여기서 외래키 관리를 주인(Owner)만이 가능하고 나머지는 mappedBy를 통해 읽기만 가능하다.
//             team2.getMembers().add(member); (X)

            // member에서 팀을 셋팅한다
            // 2.team1에서 멤버를 가져와 출력한다. 그런데 안나온다?
//            member.setTeam(team1);
//            team1.getMembers().stream().forEach(data->System.out.println(data.getName()));

            // DB에는 정상으로 저장되지만 객체로서는 현재 비워져있다.
            // 3.team1의 멤버들을 가져와 add로 넣어줘야 객체도 싱크가 맞는다.
//            member.setTeam(team1);
//            team1.getMembers().add(member);
//            for (Member m : team1.getMembers()) {
//                System.out.println(m.getName());
//            }

            // 데이터 모델링으르 할 때에 단방향으로만 만들어 놓고 개발진행하면서 필요한 부분에 의해서만 양방향으로 바꿔주는 식으로 하는게 좋음


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

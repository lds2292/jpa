package hellojpa;

import hellojpa.entity.*;

import javax.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();
        try {
            Parent parent = new Parent();
            parent.setId1("myId1");
            parent.setId2("myId2");
            parent.setName("parentName");
            em.persist(parent);

            Child child = new Child();
            child.setId("childId1");
            child.setParent(parent);
            em.persist(child);

            em.flush();
            em.clear();

            ParentId parentId = new ParentId("myId1", "myID2");
            Parent findParent = em.find(Parent.class, parentId);

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

        emf.close();
    }
}

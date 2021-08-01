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
            // BOOK 생성
            Book book = new Book();
            book.setName("JPA 따라잡기");
            book.setAuthor("라이언");
            book.setPrice(12000);
            book.setIsbn("abcde");
            em.persist(book);

            // MOVIE 생성
            Movie movie = new Movie();
            movie.setActor("홍길동");
            movie.setDirector("고길동");
            movie.setName("둘리와 함께 동해번쩍 서해번쩍");
            movie.setPrice(8000);
            em.persist(movie);

            // Album 생성
            Album album = new Album();
            album.setArtist("아이유");
            album.setName("LILAC");
            album.setPrice(24000);
            em.persist(album);

            em.flush();
            em.clear();

            Album iu = em.find(Album.class, album.getId());
            System.out.println("Album name : " + iu.getName());
            System.out.println("Album Artist: " + iu.getArtist());
            System.out.println("Album Price: " + iu.getPrice());
            System.out.println("Album id: " + iu.getId());

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }

        emf.close();
    }
}

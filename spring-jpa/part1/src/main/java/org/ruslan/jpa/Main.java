package org.ruslan.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.ruslan.jpa.config.JpaConfig;
import org.ruslan.jpa.entity.Movie;
import org.ruslan.jpa.repository.MovieRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        MovieRepository mr = new MovieRepository(emf);

//        Movie m1 = new Movie(null, "The Godfather", 1972, "English");
//        Movie m2 = new Movie(null, "Dune", 2021, "English");
//
//        mr.saveMovie(m1);
//        mr.saveMovie(m2);

//        System.out.println(mr.getMovie(1L));

//        mr.removeMovie(1L);

//-----------------------------------------------------------------------------------------------------------------

// 3.	Выполнить обновление сущности по ID тремя способами. Один из них – через createQuery.

        //1 createQuery
        /*
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("UPDATE Movie m SET m.releaseYear = m.releaseYear + :increment "
                + "WHERE m.id = :id");

        query.setParameter("increment", 10);
        query.setParameter("id", "2");
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);

        em.getTransaction().commit();
        em.close();
        */

        //2 persist
        /*
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Movie m3 = em.find(Movie.class, 2L);
        m3.setReleaseYear(2021);
        em.persist(m3);

        em.getTransaction().commit();
        em.close();
        */

        //3 merge
        /*
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Movie m3 = em.find(Movie.class, 2L);
        m3.setReleaseYear(2000);
        em.merge(m3);

        em.getTransaction().commit();
        em.close();
        */
//---------------------------------------------------------------------------------------------

// 4.	Привести два-три примера оптимизации при работе с Persistence Context.
//        EntityManager em = emf.createEntityManager();
//
//        em.getTransaction().begin();

        /*
        Movie m4 = new Movie(null, "Alladin", 1999, "England");
        em.persist(m4);
        em.remove(m4);
        em.persist(m4);

        // Hibernate: insert into movies (language, movieName, releaseYear) values (?, ?, ?)
         */

        /*
        Movie m4 = new Movie(null, "Big Boss", 1970, "Chinese");
        em.persist(m4);
        em.find(Movie.class,4);

        // Hibernate: insert into movies (language, movieName, releaseYear) values (?, ?, ?)
        */
 //       em.getTransaction().commit();
 //       em.close();

//--------------------------------------------------------------------------------------------------

// 5.	Дана сущность. Необходимо данную сущность перевести из состояния Transient в Persistence,
//      затем в Removed, затем в Detached с помощью методов EntityManager.

        /*

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // transient
        Movie m5 = new Movie(null, "Big Boss", 1970, "Chinese");

        // persistent
        em.persist(m5);

        //removed
        em.remove(m5);

        //detached
        em.detach(m5);

        em.getTransaction().commit();
        em.close();

         */
    }
}

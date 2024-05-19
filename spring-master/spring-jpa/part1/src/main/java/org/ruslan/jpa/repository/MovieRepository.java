package org.ruslan.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.ruslan.jpa.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRepository {

    private final EntityManagerFactory emf;

    @Autowired
    public MovieRepository(EntityManagerFactory managerFactory) {
        emf = managerFactory;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // 2.	Выполнить сохранение, поиск, удаление с помощью EntityManager.
    public void saveMovie(Movie movie) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        em.persist(movie);

        em.getTransaction().commit();
    }

    public Movie getMovie(Long movieId) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Movie movie = em.find(Movie.class, movieId);
        em.detach(movie);

        em.getTransaction().commit();
        return movie;
    }

    public void removeMovie(Long movieId) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Movie movie = em.find(Movie.class, movieId);

        em.remove(movie);
        em.getTransaction().commit();
    }
}

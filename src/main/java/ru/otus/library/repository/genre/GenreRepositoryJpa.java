package ru.otus.library.repository.genre;

import org.springframework.stereotype.Repository;
import ru.otus.library.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            em.flush();
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre findById(long id) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id = :id", Genre.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

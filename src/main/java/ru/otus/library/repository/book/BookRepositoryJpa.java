package ru.otus.library.repository.book;

import org.springframework.stereotype.Repository;
import ru.otus.library.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            em.flush();
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override

    public Book findById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "join fetch b.author " +
                "join fetch b.genre " +
                "left join fetch b.comments where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "join fetch b.author " +
                "join fetch b.genre " +
                "left join fetch b.comments", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "join fetch b.author " +
                "join fetch b.genre " +
                "left join fetch b.comments " +
                "where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}

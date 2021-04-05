package ru.otus.library.repository.book;

import org.springframework.stereotype.Repository;
import ru.otus.library.models.Book;

import javax.persistence.*;
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
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> graph = em.createEntityGraph("book-author-genre-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b ", Book.class);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        EntityGraph<?> graph = em.createEntityGraph("book-author-genre-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "where b.title = :title", Book.class);
        query.setParameter("title", title);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.clear();
    }


}

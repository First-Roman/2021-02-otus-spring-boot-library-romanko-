package ru.otus.library.dao.book;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Repository
@AllArgsConstructor
public class BookDAOJdbc implements BookDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from book", Map.of(), Integer.class);
    }

    @Override
    public void insert(Book book) {
        namedParameterJdbcOperations.update("insert into book (id,title,author_id,genre_id)" +
                        "values (:id,:title,:authorId,:genreId)",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("update book set title = :title , " +
                        "author_id = :authorId ," +
                        "genre_id = :genreId " +
                        "where id = :id",
                Map.of("id", book.getId(),
                        "title", book.getTitle(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void delete(long id) {
        namedParameterJdbcOperations.update("delete from book where id = :id", Map.of("id", id));
    }

    @Override
    public Book getById(long id) {
        return namedParameterJdbcOperations
                .queryForObject("select b.id as id, b.title as title, " +
                                "a.id as author_id, a.first_name as first_name, " +
                                "a.last_name as last_name, a.middle_name as middle_name," +
                                "g.id as genre_id, g.genre_name as genre_name from " +
                                "book b, genre g, author a where b.id = :id " +
                                "and b.author_id = a.id " +
                                "and b.genre_id = g.id",
                        Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations
                .query("select b.id as id, b.title as title, " +
                        "a.id as author_id, a.first_name as first_name, " +
                        "a.last_name as last_name, a.middle_name as middle_name," +
                        "g.id as genre_id, g.genre_name as genre_name from " +
                        "book b, genre g, author a where  " +
                        "b.author_id = a.id " +
                        "and b.genre_id = g.id", new BookMapper());
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return namedParameterJdbcOperations
                .query("select b.id as id, b.title as title, " +
                                "a.id as author_id, a.first_name as first_name, " +
                                "a.last_name as last_name, a.middle_name as middle_name," +
                                "g.id as genre_id, g.genre_name as genre_name from " +
                                "book b, genre g, author a where b.title = :title " +
                                "and b.author_id = a.id " +
                                "and b.genre_id = g.id",
                        Map.of("title", title), new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String title = resultSet.getString("title");
            String genreName = resultSet.getString("genre_name");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String middleName = resultSet.getString("middle_name");
            Author author = new Author(authorId, firstName, lastName, middleName);
            Genre genre = new Genre(genreId, genreName);
            return new Book(id, title, author, genre);
        }
    }
}

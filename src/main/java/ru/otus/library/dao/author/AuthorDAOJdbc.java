package ru.otus.library.dao.author;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDAOJdbc implements AuthorDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from author", Map.of(), Integer.class);
    }

    @Override
    public void insert(Author author) {
        namedParameterJdbcOperations
                .update("insert into author (id, first_name, last_name, middle_name)" +
                                " values (:id,:firstName,:lastName,:middleName)",
                        Map.of("id", author.getId(),
                                "firstName", author.getFirstName(),
                                "lastName", author.getLastName(),
                                "middleName", author.getMiddleName()));
    }

    @Override
    public Author getById(long id) {
        return namedParameterJdbcOperations.queryForObject("select * from author where id = :id",
                Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from author",
                new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String middleName = resultSet.getString("middle_name");
            return new Author(id, firstName, lastName, middleName);
        }
    }
}

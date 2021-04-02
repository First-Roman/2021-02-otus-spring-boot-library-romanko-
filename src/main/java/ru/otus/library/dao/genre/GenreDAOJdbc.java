package ru.otus.library.dao.genre;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class GenreDAOJdbc implements GenreDAO {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public int count() {
        return namedParameterJdbcOperations.queryForObject("select count(*) from genre", Map.of(), Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        namedParameterJdbcOperations.update("insert into genre (id, genre_name) " +
                        "values (:id, :genreName)",
                Map.of("id", genre.getId(),
                        "genreName", genre.getGenreName()));
    }

    @Override
    public Genre getById(long id) {
        return namedParameterJdbcOperations.queryForObject("select * from genre where id = :id",
                Map.of("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select * from genre", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String genreName = resultSet.getString("genre_name");
            return new Genre(id, genreName);
        }
    }
}

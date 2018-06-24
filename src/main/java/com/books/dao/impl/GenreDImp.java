package com.books.dao.impl;

import com.books.dao.GenreD;
import com.books.model.impl.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


@Component
@Service
public class GenreDImp implements GenreD {

    private static final Logger LOGGER = LogManager.getLogger(GenreDImp.class);

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;


    @Autowired
    public GenreDImp(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Genre findById(long id) {
        LOGGER.debug("searching for genre " + id);

        return jdbcTemplate.queryForObject(FIND_GENRE_BY_ID, new Object[]{id}, new GenreDImp.GenreMapper());
    }

    @Override
    public Set<Genre> findAll() {
        LOGGER.debug("get list of all the genres");
        return new HashSet<Genre>(jdbcTemplate.query(FIND_ALL_GENRES, new GenreDImp.GenreMapper())) ;
    }

    @Override
    public Set<Genre> findGenresByBook(long id){
        LOGGER.debug("get list of all genres by book "+id);
        return new HashSet<Genre>(jdbcTemplate.query(FIND_GENRES_BY_BOOK_ID,new Object[]{id}, new GenreDImp.GenreMapper()));
    }


    private class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            Genre genre = new Genre.GenreBuilder(name).id(id).build();

            return genre;
        }

    }

    private class GenreNewIdMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("new_genre_id");
        }
    }

}

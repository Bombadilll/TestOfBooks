package com.books.dao.impl;

import com.books.dao.AuthorD;
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
public class AuthorDImp implements AuthorD {


    private static final Logger LOGGER = LogManager.getLogger(GenreDImp.class);

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public AuthorDImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public com.books.model.impl.Author findById(long id) {
        LOGGER.debug("searching for authorD " + id);

        return jdbcTemplate.queryForObject(FIND_AUTHOR_BY_ID, new Object[]{id}, new AuthorDImp.AuthorMapper());
    }

    @Override
    public Set<com.books.model.impl.Author> findAll() {
        LOGGER.debug("get list of all the authors");
        return new HashSet<com.books.model.impl.Author>(jdbcTemplate.query(FIND_ALL_AUTHORS, new AuthorMapper()));
    }

    @Override
    public Set<com.books.model.impl.Author> findAuthorsByBook(long id){
        LOGGER.debug("get list of all authors by book "+id);
        return new HashSet<com.books.model.impl.Author>(jdbcTemplate.query(FIND_AUTHORS_BY_BOOK_ID,new Object[]{id}, new AuthorMapper()));
    }


    private class AuthorMapper implements RowMapper<com.books.model.impl.Author> {
        @Override
        public com.books.model.impl.Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            com.books.model.impl.Author author = new com.books.model.impl.Author.AuthorBuilder(name).id(id).build();

            return author;
        }

    }

    private class AuthorNewIdMapper implements RowMapper<Long> {
        @Override
        public Long mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getLong("new_author_id");
        }
    }

}

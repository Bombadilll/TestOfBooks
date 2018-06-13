package com.books.model.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by AdminPC on 03.07.2017.
 */
public class Author extends AbstModelImpl {

    private static final Logger LOGGER = LogManager.getLogger(Author.class);



    private Author(Author.AuthorBuilder builder){
        this.setId(builder.id);
        this.setName(builder.name);
    }

    public static class AuthorBuilder{

        private long id;
        private String name;

        public AuthorBuilder(String name) {
            this.name = name;
        }

        public AuthorBuilder(Author author) {
            this.id = author.getId();
            this.name = author.getName();
        }


        public Author.AuthorBuilder id(long id) {
            LOGGER.debug("id changed");
            this.id = id;
            return this;
        }

        public Author build(){
            //validateApplicationParams();
            LOGGER.debug("AuthorD builded with success");
            return new Author(this);
        }

        private void validateGenreParams() {
            if ("".equals(this.name)) {
                IllegalArgumentException ex = new IllegalArgumentException("name should not be null");
                LOGGER.info("name not valid- name is null or empty: "+ex);
                throw ex;
            }
            LOGGER.debug("AuthorD is valid");
        }
    }
}

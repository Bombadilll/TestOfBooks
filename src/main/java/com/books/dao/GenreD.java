package com.books.dao;

import com.books.model.impl.Genre;
import java.util.Set;


public interface GenreD {

    /**
     * Метод выполняет поиск и возврат списка всех жанров
     * @return - возвращает найденный список жанров
     */
    Set<Genre> findAll();


    /**
     * Метод выполняет поиск и возврат конкретного жанра
     * @param id - идентификатор жанра
     * @return - возвращает найденный жанр
     */
    Genre findById(long id);

    /**
     * Метод выполняет поиск и возврат жанров книги
     * @param id - идентификатор книги
     * @return - возвращает найденные жанры
     */
    Set<Genre> findGenresByBook(long id);

    String GENRE_NEW_ID = "SELECT genres_seq.NEXTVAL as new_genre_id FROM dual";

    String FIND_GENRE_BY_ID="Select * from GENRES where id=?";

    String FIND_ALL_GENRES="Select * from GENRES";

    String FIND_GENRES_BY_BOOK_ID="Select g.id,g.name from GENRES g, BG bg where bg.id_genre=g.id and id_book=?";

}

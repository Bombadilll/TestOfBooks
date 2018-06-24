package com.books.dao;

import com.books.model.impl.Book;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BookD {


    /**
     * Метод выполняет поиск и возврат конкретной книги
     * @param id - идентификатор книги
     * @return - возвращает найденную книгу
     */
    Book findBookById(long id);

    /**
     * Метод выполняет добавление новой книги
     * либо редактирование существующей
     * @param book - обьект книги, который необходимо добавить/отредактировать
     * @param authors - идентификаторы авторов книги, которых необходимо добавить/отредактировать
     * @param isAuthorChanged - true - авторы книги добавились/изменились, false - если список авторов остался неизменным
     * @param genres - идентификаторы жанров книги, которых необходимо добавить/отредактировать
     * @param isGenreChanged - true - жанры книги добавились/изменились, false - если список жынров остался неизменным
     */
    void save(Book book,long[] authors, boolean isAuthorChanged,long[] genres,boolean isGenreChanged);

    /**
     * Метод выполняет удаление книги
     * @param book- объект книги, который необходимо удалить
     */
    void remove(Book book);

    /**
     * Метод выполняет поиск и возврат списка всех книг
     * @return - возвращает найденный список книг
     */
    Set<Book> findAll();

    /**
     * Метод выполняет поиск и возврат списка книг по жанру
     * @param id - идентификатор жанра
     * @return - возвращает найденные книги
     */
    Set<Book> findBookByGenre(long id);

    /**
     * Метод выполняет поиск и возврат списка книг по автору
     * @param id - идентификатор автора
     * @return - возвращает найденные книги
     */
    Set<Book> findBookByAuthor(long id);


    String BOOK_NEW_ID = "SELECT books_seq.NEXTVAL as new_book_id FROM dual";

    String FIND_BOOK_BY_ID="Select * from BOOKS where id=?";

    String FIND_ALL_BOOKS="Select * from BOOKS";

    String FIND_BOOK_BY_GENRE = "select books.id as id ,books.name as name, books.description as description,books.price as price from BOOKS,BG\n" +
            "where BG.ID_BOOK=BOOKS.ID\n" +
            "and BG.ID_GENRE=?";

    String FIND_BOOK_BY_AUTHOR = "select books.id as id ,books.name as name, books.description as description,books.price as price from BOOKS,BA" +
            " where BA.ID_BOOK=BOOKS.ID\n" +
            "    and BA.ID_AUTHOR=?";

    String INSERT_BOOK = "INSERT INTO Books VALUES (?,?,?,?)";

    String INSERT_BG = "INSERT INTO BG VALUES (?,?)";

    String INSERT_BA = "INSERT INTO BA VALUES (?,?)";

    String UPDATE_BOOK="Update Books\n" +
            "set NAME= ?,DESCRIPTION=?,PRICE=?\n" +
            "where ID=?";

    String BA_REMOVE = "DELETE FROM BA WHERE id_book = ?";

    String BG_REMOVE = "DELETE FROM BG WHERE id_book= ?";

    String BOOK_REMOVE = "DELETE FROM BOOKS WHERE id= ?";
}

package com.books.dao;

import com.books.model.impl.Order;
import java.util.Set;


public interface OrderD {

    /**
     * Метод выполняет добавление новой заявки
     * либо редактирование существующе заявки
     * @param order - обьект заявки, который необходимо добавить/отредактировать
     */
    void save(Order order);

    /**
     * Метод выполняет удаление заявки, связаной с удаленной книгой
     * @param id- идентификатор книги, которая удаляется
     */
    void removeWithBookRemoval(long id);

    /**
     * Метод выполняет поиск и возврат списка всех заявок
     * @return - возвращает найденный список заявок
     */
    Set<Order> findAll();


    /**
     * Метод выполняет поиск и возврат конкретной заявки
     * @param id - идентификатор заявки
     * @return - возвращает найденную заявку
     */
    Order findById(long id);

    String ORDER_NEW_ID = "SELECT orders_seq.NEXTVAL as new_order_id FROM dual";

    String FIND_ORDER_BY_ID="Select * from ORDERS where id=?";

    String FIND_ALL_ORDER="Select * from ORDERS";

    String ORDER_ADD = "INSERT ALL " +
            "INTO ORDERS VALUES (?,?,?,?,?,?,sysdate)" +
            "SELECT * FROM dual";

    String ORDER_REMOVE_BY_BOOK = "DELETE FROM ORDERS WHERE id_book= ?";
}

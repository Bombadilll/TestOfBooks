package com.books.dao.impl;

import com.books.dao.BookD;
import com.books.dao.OrderD;
import com.books.model.impl.Order;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Service
public class OrderDImp implements OrderD {

    private static final Logger LOGGER = LogManager.getLogger(OrderDImp.class);

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Autowired
    BookD bookD;


    @Autowired
    public OrderDImp(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(Order order) {

        if (order.getId() == 0) {
            Long newId = jdbcTemplate.queryForObject(ORDER_NEW_ID, new OrderNewIdMapper());
            LOGGER.debug("create order " + newId);
            jdbcTemplate.update(ORDER_ADD,
                    newId, order.getFirstName(),order.getLastName(),order.getAddress(),order.getIdBook(),order.getQuantity()
            );
            order.setId(newId);
        }
    }

    @Override
    public void removeWithBookRemoval(long id) {
        LOGGER.info("order removed with book "+id);
        jdbcTemplate.update(ORDER_REMOVE_BY_BOOK, id);
    }

        @Override
        public Order findById (long id){
            LOGGER.debug("searching for order " + id);

            return jdbcTemplate.queryForObject(FIND_ORDER_BY_ID, new Object[]{id}, new OrderDImp.OrderMapper());
        }


        @Override
        public Set<Order> findAll () {
            LOGGER.debug("get list of all the orders");
            return new HashSet<Order>(jdbcTemplate.query(FIND_ALL_ORDER, new OrderDImp.OrderMapper()));
        }


        private class OrderMapper implements RowMapper<Order> {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                Long idbook = resultSet.getLong("id_book");
                int quant = resultSet.getInt("quant");
                Date date = resultSet.getDate("order_date");

                Order order = new Order.OrderBuilder(name,lastName,idbook).id(id).address(address).quantity(quant).bookName(bookD.findBookById(idbook).getName()).orderDate(date).build();

                return order;
            }

        }

        private class OrderNewIdMapper implements RowMapper<Long> {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("new_order_id");
            }
        }

    }

package lk.ijse.dao;

import lk.ijse.entity.OrderDTO;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;

import java.sql.SQLException;

public interface OrderDAO {
    boolean save(Order order) throws SQLException;

    boolean save(OrderDTO order) throws SQLException;

    boolean saveOrderDetail(OrderDetails orderDetails) throws SQLException;

    String generateNewID() throws SQLException;

    // Add other necessary methods for Order DAO
}

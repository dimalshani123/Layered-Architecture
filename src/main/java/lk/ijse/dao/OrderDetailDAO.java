package lk.ijse.dao;

import lk.ijse.model.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException;

    // Add other necessary methods for Order Detail DAO
}

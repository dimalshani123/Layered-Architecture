package lk.ijse.dao;

import lk.ijse.model.PlaceOrder;

import java.sql.SQLException;

public interface PlaceOrderDAO {
    boolean placeOrder(PlaceOrder placeOrder) throws SQLException;

    // Add other necessary methods for Place Order DAO
}

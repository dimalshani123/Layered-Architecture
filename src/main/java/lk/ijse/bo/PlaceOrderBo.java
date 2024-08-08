package lk.ijse.bo;

import lk.ijse.model.PlaceOrder;

import java.sql.SQLException;

public interface PlaceOrderBo {
    boolean placeOrder(PlaceOrder placeOrder) throws SQLException;

    // Add other necessary methods for Place Order BO
}

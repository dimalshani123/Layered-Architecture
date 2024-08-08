package lk.ijse.bo;

import lk.ijse.model.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBo {
    boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException;

    // Add other necessary methods for Order Detail BO
}

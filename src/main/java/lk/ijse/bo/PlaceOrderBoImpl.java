package lk.ijse.bo;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;
import lk.ijse.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    @Override
    public boolean placeOrder(PlaceOrder placeOrder) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = saveOrder(placeOrder.getOrder());
            if (isOrderSaved) {
                boolean isOrderDetailSaved = saveOrderDetails(placeOrder.getOrderDetailsList());
                if (isOrderDetailSaved) {
                    boolean isItemUpdated = updateItems(placeOrder.getOrderDetailsList());
                    if (isItemUpdated) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean saveOrder(Order order) throws SQLException {
        // Implement saving order to database
        return false;  // Replace with actual implementation
    }

    private boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        // Implement saving order details to database
        return false;  // Replace with actual implementation
    }

    private boolean updateItems(List<OrderDetails> orderDetailsList) throws SQLException {
        // Implement updating item quantities in database
        return false;  // Replace with actual implementation
    }

    // Implement other methods as needed
}

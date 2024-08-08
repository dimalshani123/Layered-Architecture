package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;
import lk.ijse.model.PlaceOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {

    @Override
    public boolean placeOrder(PlaceOrder placeOrder) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = saveOrder(placeOrder.getOrder(), connection);
            if (isOrderSaved) {
                boolean isOrderDetailSaved = saveOrderDetails(placeOrder.getOrderDetailsList(), connection);
                if (isOrderDetailSaved) {
                    boolean isItemUpdated = updateItems(placeOrder.getOrderDetailsList(), connection);
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

    private boolean saveOrder(Order order, Connection connection) throws SQLException {
        String sql = "INSERT INTO Orders VALUES (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, order.getOrderId());
        stm.setString(2, order.getDescription());
        stm.setString(3, order.getDate());
        stm.setString(4, order.getCid());
        return stm.executeUpdate() > 0;
    }

    private boolean saveOrderDetails(List<OrderDetails> orderDetailsList, Connection connection) throws SQLException {
        String sql = "INSERT INTO OrderDetails VALUES (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        for (OrderDetails od : orderDetailsList) {
            stm.setString(1, od.getOrderId());
            stm.setString(2, od.getItemId());
            stm.setDouble(3, od.getQty());
            stm.setDouble(4, od.getUnitPrice());
            stm.addBatch();
        }
        int[] results = stm.executeBatch();
        for (int result : results) {
            if (result <= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean updateItems(List<OrderDetails> orderDetailsList, Connection connection) throws SQLException {
        // Implement updating item quantities in database
        return false;  // Replace with actual implementation
    }

    // Implement other methods as needed
}

package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
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

    // Implement other methods as needed
}

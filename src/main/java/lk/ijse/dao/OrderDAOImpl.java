package lk.ijse.dao;

import lk.ijse.db.DbConnection;
import lk.ijse.entity.OrderDTO;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO Orders VALUES (?, ?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, order.getOrderId());
        stm.setString(2, order.getDescription());
        stm.setString(3, order.getDate());
        stm.setString(4, order.getCid());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean save(OrderDTO order) throws SQLException {
        return false;
    }

    @Override
    public boolean saveOrderDetail(OrderDetails orderDetails) throws SQLException {
        // Implement as needed
        return false;
    }

    @Override
    public String generateNewID() throws SQLException {
        String sql = "SELECT concat('O', max(cast(substring(orderId, 2) as unsigned))) as max_order_id FROM Orders";
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("max_order_id");
        }
        return null;
    }

    // Implement other methods as needed
}

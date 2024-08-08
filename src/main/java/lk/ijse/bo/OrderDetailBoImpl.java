package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.OrderDetailDAO;
import lk.ijse.model.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBoImpl implements OrderDetailBo {
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public boolean saveOrderDetails(List<OrderDetails> orderDetailsList) throws SQLException {
        return orderDetailDAO.saveOrderDetails(orderDetailsList);
    }

    // Implement other methods as needed
}

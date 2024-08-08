package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.OrderDAO;

import java.sql.SQLException;

public class OrderBoImpl implements OrderBo {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String generateNewID() throws SQLException {
        return orderDAO.generateNewID();
    }

    // Implement other methods as needed
}

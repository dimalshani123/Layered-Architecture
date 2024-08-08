package lk.ijse.bo;

import java.sql.SQLException;

public interface OrderBo {
    String generateNewID() throws SQLException;

    // Add other necessary methods for Order BO
}

package lk.ijse.bo;

import lk.ijse.entity.CustomerDTO;
import lk.ijse.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto) throws SQLException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    Customer searchCustomer(String id) throws SQLException;

}

package lk.ijse.bo;

import lk.ijse.dao.CustomerDAO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.entity.CustomerDTO;
import lk.ijse.model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    static CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    //CustomerBo customerBo= DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();
        ArrayList<Customer> all = (ArrayList<Customer>) customerDAO.getAll();
        for (Customer c : all) {
            allCustomer.add(new CustomerDTO(c.getCustomerId(), c.getName(), c.getAddress(), c.getContact()));
        }
        return allCustomer;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getCustomerId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getCustomerId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public Customer searchCustomer(String id) throws SQLException {
        return customerDAO.search(id);
    }

}









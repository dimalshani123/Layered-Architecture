package lk.ijse.dao;

import lk.ijse.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()) {
            Customer customer = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getString("contact"));
            allCustomers.add(customer);
        }
        return allCustomers;
    }


    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }


    @Override
    public boolean save(Customer entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Customer VALUES(?, ?, ?, ?)", entity.getCustomerId(), entity.getName(), entity.getAddress(),entity.getContact());
    }

    @Override
    public boolean update(Customer dto) throws SQLException {
        return SQLUtil.execute("UPDATE Customer SET name = ?, address = ?, contact = ?  WHERE customerId = ?", dto.getName(), dto.getAddress(),dto.getContact(),dto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Customer WHERE customerId=?", id);
    }

    @Override
    public Customer search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE customerId = ?", id + "");
        rst.next();
        return new Customer(id + "", rst.getString("name"), rst.getString("address"), rst.getString("contact"));
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }


}
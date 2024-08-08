package lk.ijse.dao;

import lk.ijse.model.Customer;
import lk.ijse.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SuperDAO<E> {
    ArrayList<Employee> getAll() throws SQLException;

    List<String> getIds() throws SQLException;

    String getCurrentId() throws SQLException;

    boolean save(Employee entity) throws SQLException;

    boolean save(Customer entity) throws SQLException;

    boolean update(String id) throws SQLException;

    boolean update(Employee entity) throws SQLException;

    boolean delete(String id) throws SQLException;

    Customer search(String id) throws SQLException;

    int getCount() throws SQLException;
}

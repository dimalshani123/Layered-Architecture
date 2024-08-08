package lk.ijse.dao;

import lk.ijse.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()) {
            Employee employee = new Employee(rst.getString("id"), rst.getString("name"), rst.getString("age"), rst.getString("address"));
            allEmployee.add(employee);
        }
        return allEmployee;
    }
    

    @Override
    public String getCurrentId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Employee ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString("id");
        }
        return null;
    }

    @Override
    public boolean save(Employee entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Employee VALUES(?, ?, ?, ?)", entity.getEmployeeId(), entity.getName(), entity.getAge(), entity.getAddress());
    }



    @Override
    public boolean update(Employee entity) throws SQLException {
        return SQLUtil.execute("UPDATE Employee SET name = ?, age = ?, address = ? WHERE id = ?", entity.getName(), entity.getAge(), entity.getAddress(), entity.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Employee WHERE id = ?", id);
    }

    @Override
    public Employee search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE id = ?", id);
        if (rst.next()) {
            return new Employee(id, rst.getString("name"), rst.getString("age"), rst.getString("address"));
        }
        return null; // or throw an exception if not found
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM Employee");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}

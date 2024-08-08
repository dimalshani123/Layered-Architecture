package lk.ijse.bo;

import lk.ijse.entity.EmployeeDTO;
import lk.ijse.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo {
    ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO dto) throws SQLException;

    boolean updateEmployee(Employee dto) throws SQLException;

    boolean deleteEmployee(String id) throws SQLException;

    Employee searchEmployee(String id) throws SQLException;
}

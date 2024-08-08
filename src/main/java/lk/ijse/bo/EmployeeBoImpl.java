package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.EmployeeDAO;
import lk.ijse.entity.EmployeeDTO;
import lk.ijse.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        ArrayList<Employee> all = (ArrayList<Employee>) employeeDAO.getAll();
        for (Employee e : all) {
            allEmployee.add(new EmployeeDTO(e.getEmployeeId(), e.getName(), e.getAge(), e.getAddress()));
        }
        return allEmployee;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException {
        return employeeDAO.save(new Employee(dto.getEmployeeId(), dto.getName(), dto.getAge(), dto.getAddress()));
    }

    @Override
    public boolean updateEmployee(Employee dto) throws SQLException {
        return employeeDAO.update(new Employee(dto.getEmployeeId(), dto.getName(), dto.getAge(), dto.getAddress()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public Employee searchEmployee(String id) throws SQLException {
        return employeeDAO.search(id);
    }

}









package lk.ijse.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T>{

    public  boolean save(T dto) throws SQLException;

    public  boolean update(T dto) throws SQLException;

    public  boolean delete(String id) throws SQLException;

    public List<T> getAll() throws SQLException;

    public  String getCurrentId() throws SQLException;
    public  T search(String id) throws SQLException;
    public  int getCount() throws SQLException;
}

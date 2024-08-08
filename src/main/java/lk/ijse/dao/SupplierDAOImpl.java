package lk.ijse.dao;

import lk.ijse.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        while (rst.next()) {
            Supplier supplier= new Supplier(rst.getString("id"), rst.getString("name"), rst.getString("location"), rst.getString("contact"));
            allSupplier.add(supplier);
        }
        return allSupplier;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }


    @Override
    public boolean save(Supplier entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES(?, ?, ?, ?)", entity.getSupplierId(), entity.getName(), entity.getLocation(),entity.getContact());
    }

    @Override
    public boolean update(Supplier dto) throws SQLException {
        return SQLUtil.execute("UPDATE Supplier SET name = ?, location = ?, contact = ?  WHERE supplierId = ?", dto.getName(), dto.getLocation(),dto.getContact() , dto.getSupplierId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM Supplier WHERE supplierId = ?", id);
    }

    @Override
    public Supplier search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier WHERE supplierId = ?", id + "");
        rst.next();
        return new Supplier(id + "", rst.getString("name"), rst.getString("location"), rst.getString("contact"));
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }


}
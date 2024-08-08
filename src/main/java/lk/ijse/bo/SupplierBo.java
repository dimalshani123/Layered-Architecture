package lk.ijse.bo;

import lk.ijse.entity.SupplierDTO;
import lk.ijse.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo {
    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO dto) throws SQLException;

    boolean updateSupplier(Supplier dto) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException;

    Supplier searchSupplier(String id) throws SQLException;
}

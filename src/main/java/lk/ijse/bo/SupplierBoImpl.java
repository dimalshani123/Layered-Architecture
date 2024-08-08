package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SupplierDAO;
import lk.ijse.entity.SupplierDTO;
import lk.ijse.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBoImpl implements SupplierBo {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSupplier = new ArrayList<>();
        ArrayList<Supplier> all = (ArrayList<Supplier>) supplierDAO.getAll();
        for (Supplier s : all) {
            allSupplier.add(new SupplierDTO(s.getSupplierId(), s.getName(), s.getLocation(), s.getContact()));
        }
        return allSupplier;
    }

    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getSupplierId(), dto.getName(), dto.getLocation(), dto.getContact()));
    }

    @Override
    public boolean updateSupplier(Supplier dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(), dto.getName(), dto.getLocation(), dto.getContact()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public Supplier searchSupplier(String id) throws SQLException {
        return supplierDAO.search(id);
    }

}









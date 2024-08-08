package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.PrescriptionDAO;
import lk.ijse.entity.PrescriptionDTO;
import lk.ijse.model.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionBoImpl implements PrescriptionBo {
    PrescriptionDAO prescriptionDAO = (PrescriptionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRESCRIPTION);

    @Override
    public ArrayList<PrescriptionDTO> getAllPrescription() throws SQLException, ClassNotFoundException {
        ArrayList<PrescriptionDTO> allPrescription = new ArrayList<>();
        ArrayList<Prescription> all = (ArrayList<Prescription>) prescriptionDAO.getAll();
        for (Prescription p : all) {
            allPrescription.add(new PrescriptionDTO(p.getPrescriptionId(), p.getDescription(), p.getDate(), p.getNameOfDocter()));
        }
        return allPrescription;
    }

    public boolean savePrescription(PrescriptionDTO dto) throws SQLException {
        return prescriptionDAO.save(new Prescription(dto.getPrescriptionId(), dto.getDescription(), dto.getDate(), dto.getNameOfDocter()));
    }

    @Override
    public boolean updatePrescription(PrescriptionDTO dto) throws SQLException {
        return prescriptionDAO.update(new Prescription(dto.getPrescriptionId(), dto.getDescription(), dto.getDate(), dto.getNameOfDocter()));
    }

    @Override
    public boolean deletePrescription(String id) throws SQLException {
        return prescriptionDAO.delete(id);
    }

    @Override
    public Prescription searchPrescription(String id) throws SQLException {
        return prescriptionDAO.search(id);
    }
}

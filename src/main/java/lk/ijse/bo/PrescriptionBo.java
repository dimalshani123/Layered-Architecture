package lk.ijse.bo;

import lk.ijse.entity.PrescriptionDTO;
import lk.ijse.model.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PrescriptionBo {
    ArrayList<PrescriptionDTO> getAllPrescription() throws SQLException, ClassNotFoundException;

    boolean savePrescription(PrescriptionDTO dto) throws SQLException;

    boolean updatePrescription(PrescriptionDTO dto) throws SQLException;

    boolean deletePrescription(String id) throws SQLException;

    Prescription searchPrescription(String id) throws SQLException;
}

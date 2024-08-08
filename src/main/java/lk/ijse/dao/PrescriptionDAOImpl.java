package lk.ijse.dao;

import lk.ijse.model.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionDAOImpl implements PrescriptionDAO {
    @Override
    public ArrayList<Prescription> getAll() throws SQLException {
        ArrayList<Prescription> allPrescription = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Prescription");
        while (rst.next()) {
            Prescription prescription = new Prescription(rst.getString("id"), rst.getString("description"), rst.getString("date"), rst.getString("nameOfDoctor"));
            allPrescription.add(prescription);
        }
        return allPrescription;
    }



    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }


    @Override
    public boolean save(Prescription entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Prescription VALUES(?, ?, ?, ?)", entity.getPrescriptionId(), entity.getDescription(), entity.getDate(),entity.getNameOfDocter());
    }

    @Override
    public boolean update(Prescription dto) throws SQLException {
        return SQLUtil.execute("UPDATE Prescription SET description = ?, date = ?, nameOfDocter= ? WHERE prescriptionId = ?", dto.getDescription(), dto.getDate(),dto.getNameOfDocter(),  dto.getPrescriptionId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Prescription WHERE prescriptionId = ?", id);
    }

    @Override
    public Prescription search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Prescription WHERE prescriptionId = ?", id + "");
        rst.next();
        return new Prescription(id + "", rst.getString("description"), rst.getString("date"), rst.getString("nameOfDoctor"));
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }


}
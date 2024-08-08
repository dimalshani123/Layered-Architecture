package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.PrescriptionBo;
import lk.ijse.bo.PrescriptionBoImpl;
import lk.ijse.entity.PrescriptionDTO;
import lk.ijse.model.Prescription;

import java.sql.SQLException;

public class PrescriptionController {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtNameOfDocter;

    @FXML
    private TextField txtPrescriptionId;

    private final PrescriptionBo prescriptionBo = new PrescriptionBoImpl();


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }
    private void clearFields() {
        txtPrescriptionId.setText("");
        txtDescription.setText("");
        txtDate.setText("");
        txtNameOfDocter.setText("");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtPrescriptionId.getText();
        String description = txtDescription.getText();
        String date = txtDate.getText();
        String nameOfDoctor = txtNameOfDocter.getText();

      PrescriptionDTO prescription = new PrescriptionDTO(id, description, date, nameOfDoctor);

        try {
            boolean isSaved =prescriptionBo.savePrescription(prescription);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Prescription saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save prescription!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtPrescriptionId.getText();

        try {
            boolean isDeleted = prescriptionBo.deletePrescription(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Prescription deleted!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete prescription!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtPrescriptionId.getText();

        try {
            Prescription prescription = prescriptionBo.searchPrescription(id);
            if (prescription != null) {
                txtPrescriptionId.setText(prescription.getPrescriptionId());
                txtDescription.setText(prescription.getDescription());
                txtDate.setText(prescription.getDate());
                txtNameOfDocter.setText(prescription.getNameOfDocter());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "prescription not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }




    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtPrescriptionId.getText();
        String description = txtDescription.getText();
        String date = txtDate.getText();
        String nameOfDoctor = txtNameOfDocter.getText();

        PrescriptionDTO prescriptionDTO = new PrescriptionDTO(id, description, date, nameOfDoctor);

        try {
            boolean isUpdated = prescriptionBo.updatePrescription(prescriptionDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Prescription updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update prescription!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void txtDateOnAction(ActionEvent event) {
        txtNameOfDocter.requestFocus();

    }

    @FXML
    void txtDescriptionOnAction(ActionEvent event) {
        txtDate.requestFocus();

    }
    @FXML
    void txtNameOfDocterOnAction(ActionEvent event) {
        txtPrescriptionId.requestFocus();


    }


    @FXML
    void txtPrescriptionIdOnAction(ActionEvent event) {
        txtDescription.requestFocus();

    }


    public void initialize() {

        addRegex(txtPrescriptionId);

    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtPrescriptionId && !newValue.matches("^P.*$")) {
                //new Alert(Alert.AlertType.INFORMATION, "Start with P").show();
                txtPrescriptionId.setStyle("-fx-focus-color:#f21e0f");
                txtPrescriptionId.clear();
            } else {
                txtPrescriptionId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });
    }
}



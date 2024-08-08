package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.SupplierBo;
import lk.ijse.bo.SupplierBoImpl;
import lk.ijse.entity.SupplierDTO;
import lk.ijse.model.Supplier;

import java.sql.SQLException;

public class SupplierController {

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
    private TextField txtContact;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSupplierId;


    private final SupplierBo supplierBo = new SupplierBoImpl();


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }
    private void clearFields() {
        txtSupplierId.setText("");
        txtName.setText("");
        txtLocation.setText("");
        txtContact.setText("");

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        try {
            boolean isDeleted = supplierBo.deleteSupplier(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete supplier!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtName.getText();
        String location = txtLocation.getText();
        String contact = txtContact.getText();

        SupplierDTO supplierDTO = new SupplierDTO(id, name, location, contact);

        try {
            boolean isSaved = supplierBo.saveSupplier(supplierDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save suppler!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        try {
            Supplier supplier = supplierBo.searchSupplier(id);
            if (supplier != null) {
                txtSupplierId.setText(supplier.getSupplierId());
                txtName.setText(supplier.getName());
                txtLocation.setText(supplier.getLocation());
                txtContact.setText(supplier.getContact());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtName.getText();
        String location = txtLocation.getText();
        String contact = txtContact.getText();

        Supplier supplier = new Supplier(id, name,location, contact);

        try {
            boolean isUpdated = supplierBo.updateSupplier(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update supplier!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void txtContactOnAction(ActionEvent event) {
        txtSupplierId.requestFocus();

    }

    @FXML
    void txtLocationOnaction(ActionEvent event) {
        txtContact.requestFocus();

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtLocation.requestFocus();

    }

    @FXML
    void txtSupplierIdOnAction(ActionEvent event) {
        txtName.requestFocus();

    }
    public void initialize() {

        addRegex(txtSupplierId);
        addRegex(txtContact);

    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtSupplierId && !newValue.matches("^S.*$")){
                //new Alert(Alert.AlertType.INFORMATION,"Start with S").show();
                txtSupplierId.setStyle("-fx-focus-color:#f21e0f");
                txtSupplierId.clear();
            } else {
                txtSupplierId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtContact && !newValue.matches("^\\+?[0-9\\s-]+$")){
                //new Alert(Alert.AlertType.INFORMATION,"example - +765823106 or 0765823106").show();
                txtSupplierId.setStyle("-fx-focus-color:#f21e0f");
                txtContact.clear();
            } else {
                txtSupplierId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });
    }


}

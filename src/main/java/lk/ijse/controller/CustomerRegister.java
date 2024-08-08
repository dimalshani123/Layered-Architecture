package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.CustomerBo;
import lk.ijse.bo.CustomerBoImpl;
import lk.ijse.entity.CustomerDTO;
import lk.ijse.model.Customer;

import java.sql.SQLException;

public class CustomerRegister {

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
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtName;

    private final CustomerBo customerBo = new CustomerBoImpl();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try {
            boolean isDeleted = customerBo.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, contact);

        try {
            boolean isSaved = customerBo.saveCustomer(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try {
            Customer customer = customerBo.searchCustomer(id);
            if (customer != null) {
                txtCustomerId.setText(customer.getCustomerId());
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtContact.setText(customer.getContact());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Customer not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, contact);

        try {
            boolean isUpdated = customerBo.updateCustomer(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer! Please check if the customer ID exists.").show();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        } catch (Exception e) {
            e.printStackTrace(); // Log any other exceptions for debugging purposes
            new Alert(Alert.AlertType.ERROR, "Unexpected error: " + e.getMessage()).show();
        }
    }




    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        txtCustomerId.requestFocus();
    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    public void initialize() {
        addRegex(txtCustomerId);
        addRegex(txtContact);
    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField == txtCustomerId && !newValue.matches("^C.*$")) {
                txtCustomerId.setStyle("-fx-focus-color:#f21e0f");
                txtCustomerId.clear();
            } else {
                txtCustomerId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField == txtContact && !newValue.matches("^\\+?[0-9\\s-]+$")) {
                txtContact.setStyle("-fx-focus-color:#f21e0f");
                txtContact.clear();
            } else {
                txtContact.setStyle("-fx-focus-color:#C7F6C7");
            }
        });
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }
}

package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.EmployeeBo;
import lk.ijse.bo.EmployeeBoImpl;
import lk.ijse.entity.EmployeeDTO;
import lk.ijse.model.Employee;

import java.sql.SQLException;

public class EmployeeRegister {
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
    private TextField txtAge;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;



    private final EmployeeBo employeeBo = new EmployeeBoImpl();


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            boolean isDeleted = employeeBo.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String address = txtAddress.getText();

        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, age, address);

        try {
            boolean isSaved = employeeBo.saveEmployee(employeeDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String age = txtAge.getText();
        String address = txtAddress.getText();

        Employee employee = new Employee(id, name,age, address);

        try {
            boolean isUpdated = employeeBo.updateEmployee(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update employee!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtEmployeeId.getText();

        try {
            Employee employee = employeeBo.searchEmployee(id);
            if (employee != null) {
                txtEmployeeId.setText(employee.getEmployeeId());
                txtName.setText(employee.getName());
                txtAge.setText(employee.getAge());
                txtAddress.setText(employee.getAddress());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }
    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtAddress.setText("");

    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtEmployeeId.requestFocus();

    }

    @FXML
    void txtAgeOnAction(ActionEvent event) {
        txtAddress.requestFocus();

    }

    @FXML
    void txtEmployeeIdOnAction(ActionEvent event) {
        txtName.requestFocus();

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtAge.requestFocus();

    }


    public void initialize() {

        addRegex(txtEmployeeId);
        // addRegex(txtName);
        //addRegex(txtAge);
        // addRegex(txtAddress);

    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtEmployeeId && !newValue.matches("^E.*$")) {
                // new Alert(Alert.AlertType.INFORMATION, "Start with E").show();
                txtEmployeeId.setStyle("-fx-focus-color:#f21e0f");
                txtEmployeeId.clear();
            } else {
                txtEmployeeId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });

      /*  textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtName && !newValue.matches("^[A-Za-z]+(?:[\s-][A-Za-z]+)*$")) {
                new Alert(Alert.AlertType.INFORMATION, "First letter should be capital").show();
                txtName.clear();
            }
        });*/

//        textField.textProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (textField == txtAge && !newValue.matches("^(?:1[0-4]?\\d|150|[1-9]?\\d)$")) {
//               // new Alert(Alert.AlertType.INFORMATION, "example- 6,15,102").show();
//                txtAge.setStyle("-fx-focus-color:#f21e0f");
//                txtAge.clear();
//            }
//        });

        /*textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtAddress && !newValue.matches("^[\\w\\s\\.,#\\-\\/]+$")) {
                new Alert(Alert.AlertType.INFORMATION, "example- 123 Main Street or Matara").show();
                txtAddress.clear();
            }
        });*/

    }

}

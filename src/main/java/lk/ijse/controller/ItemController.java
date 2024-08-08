package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.ItemBo;
import lk.ijse.bo.ItemBoImpl;
import lk.ijse.entity.ItemDTO;
import lk.ijse.model.Item;

import java.sql.SQLException;

public class ItemController {
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
    private TextField txtDescription;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    private final ItemBo itemBo = new ItemBoImpl();


    @FXML
    void txtDescriptionOnAction(ActionEvent event) {
        txtItemId.requestFocus();

    }

    @FXML
    void txtItemIdOnAction(ActionEvent event) {
        txtName.requestFocus();

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtQty.requestFocus();

    }

    @FXML
    void txtPriceOnAction(ActionEvent event) {
        txtDescription.requestFocus();

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        txtPrice.requestFocus();

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtItemId.setText("");
        txtName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtDescription.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            boolean isDeleted = itemBo.deleteItem(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete item!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String name = txtName.getText();
        Integer qty= Integer.valueOf(txtQty.getText());
      Double price = Double.valueOf(txtPrice.getText());
        String description = txtDescription.getText();

        ItemDTO itemDTO = new ItemDTO(id, name, qty, price,description);

        try {
            boolean isSaved = itemBo.saveItem(itemDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save item!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            Item item = itemBo.searchItem(id);
            if (item != null) {
                txtItemId.setText(item.getItemId());
                txtName.setText(item.getName());
                txtQty.setText(String.valueOf(item.getQty()));
                txtPrice.setText(String.valueOf(item.getPrice()));
                txtDescription.setText(item.getDescription());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Item  not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtItemId.getText();
        String name = txtName.getText();
        Integer qty = Integer.valueOf(txtQty.getText());
        Double price  = Double.valueOf(txtPrice.getText());
        String description=txtDescription.getText();

        ItemDTO item = new ItemDTO(id, name,qty,price,description);

        try {
            boolean isUpdated = itemBo.updateItem(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update item!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    public void initialize() {

        addRegex(txtItemId);
    }

    private void addRegex(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtItemId && !newValue.matches("^I.*$")) {
                txtItemId.setStyle("-fx-focus-color:#f21e0f");
                txtItemId.clear();
            } else {
                txtItemId.setStyle("-fx-focus-color:#C7F6C7");
            }
        });
    }
}


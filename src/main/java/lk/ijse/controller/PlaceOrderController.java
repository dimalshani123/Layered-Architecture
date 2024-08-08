package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.PlaceOrderBo;
import lk.ijse.bo.PlaceOrderBoImpl;
import lk.ijse.model.Item;
import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;
import lk.ijse.model.PlaceOrder;
import lk.ijse.tm.CartTm;
import net.sf.jasperreports.engine.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlaceOrderController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox<String> cmbCode;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableView<CartTm> tblOrderCart;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblNetTotal;
    @FXML
    private Label lblCustomerName;

    @FXML
    private Button btnPrintBill;
    @FXML
    private Label lblQtyOnHand;

    private final PlaceOrderBo placeOrderBo = new PlaceOrderBoImpl();
    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();



    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerIds();
        getItemCodes();
        setCellValueFactory();
    }

    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            // Replace with appropriate logic to fetch customer IDs
            // List<String> idList = CustomerRepo.getIds();

            // Example:
            List<String> idList = Arrays.asList("C001", "C002", "C003");

            obList.addAll(idList);
            cmbCustomerId.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }
    }

    private String generateNextOrderId(String currentId) {
        // Replace with actual implementation
        return "O1";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void getCurrentOrderId() {
        // Replace with actual implementation
        lblOrderId.setText("O1");
    }

    private  void getQtyOnHand(){

    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbCode.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;

        // Replace with actual implementation
        JFXButton btnRemove = new JFXButton("remove");

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex() + 1;
                System.out.println("Selected Index : " + selectedIndex);
                obList.remove(selectedIndex);

                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colCode.getCellData(i))) {
                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;

                tm.setQty(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();
                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
        obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTm item : obList) {
            netTotal += item.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String desc = lblDescription.getText();
        String date = String.valueOf(Date.valueOf(LocalDate.now()));
        String cid = cmbCustomerId.getValue();

        Order order = new Order(orderId, desc, date, cid);

        List<OrderDetails> odList = new ArrayList<>();

        for (CartTm tm : obList) {
            OrderDetails od = new OrderDetails(orderId, tm.getCode(), tm.getQty(), tm.getPrice());
            odList.add(od);
        }

        PlaceOrder placeOrder = new PlaceOrder(order, odList);

        try {
            boolean isPlaced = placeOrderBo.placeOrder(placeOrder);
            if (isPlaced) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            // Replace with appropriate logic to fetch item codes
            // List<String> itemList = ItemRepo.getIds();

            // Example:
            List<String> itemList = Arrays.asList("I001", "I002", "I003");

            obList.addAll(itemList);
            cmbCode.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }
    }

    @FXML
    void cmbCodeOnAction(ActionEvent event) {
        String code = cmbCode.getValue();

        try {
            // Replace with appropriate logic to search item by code
            // Item item = ItemRepo.searchById(code);

            // Example:
            Item item = new Item(code, "Sample Item", 100, 250.00);

            if (item != null) {
                lblDescription.setText(item.getDescription());
                lblUnitPrice.setText(String.valueOf(item.getPrice()));
                 lblQtyOnHand.setText(String.valueOf(item.getQty()));  // Assuming no UI field for qty on hand
            }

            txtQty.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        try {
            // Replace with appropriate logic to search customer by ID
            // Customer customer = CustomerRepo.searchById(id);

            // Example:
            String customerName = "Sample Customer";  // Replace with actual logic
            lblCustomerName.setText(customerName);

        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }
    }

    @FXML
    void btnPrintBillOnAction(ActionEvent event) {
        try {
            printBill();
        } catch (Exception e) {
            e.printStackTrace();  // Handle exception
        }
    }

    public void printBill() throws JRException, SQLException {
        // Replace with actual logic to generate and view bill
    }
}

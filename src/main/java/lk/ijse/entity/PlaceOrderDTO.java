package lk.ijse.entity;

import lk.ijse.model.Order;
import lk.ijse.model.OrderDetails;

import java.util.List;

public class PlaceOrderDTO {
    private Order order;
    private List<OrderDetails>orderDetailsList;

    public PlaceOrderDTO(Order order, List<OrderDetails> orderDetailsList) {
        this.order = order;
        this.orderDetailsList = orderDetailsList;
    }

    public PlaceOrderDTO(String orderId, String desc, String date, String cid, List<OrderDetailDTO> orderDetailsList) {
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "order=" + order +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }
}

package lk.ijse.dao;

import lk.ijse.model.Item;
import lk.ijse.model.OrderDetails;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateSet(List<OrderDetails> orderDetailsList);
}



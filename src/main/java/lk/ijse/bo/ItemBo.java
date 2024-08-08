package lk.ijse.bo;

import lk.ijse.entity.ItemDTO;
import lk.ijse.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo {
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO dto) throws SQLException;

    boolean updateItem(ItemDTO dto) throws SQLException;

    boolean deleteItem(String id) throws SQLException;

    Item searchItem(String id) throws SQLException;
}

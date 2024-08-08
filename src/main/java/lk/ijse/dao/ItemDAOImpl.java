package lk.ijse.dao;

import lk.ijse.model.Item;
import lk.ijse.model.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ArrayList<Item> allItem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            Item item = new Item(rst.getString("id"), rst.getString("name"), rst.getInt("qty"), rst.getDouble("price"), rst.getString("description"));
            allItem.add(item);
        }
        return allItem;
    }

    @Override
    public String getCurrentId() throws SQLException {
        return null;
    }


    @Override
    public boolean save(Item entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Item VALUES(?, ?, ?, ?,?)", entity.getItemId(), entity.getName(), entity.getQty(),entity.getPrice(),entity.getDescription());
    }

    @Override
    public boolean update(Item dto) throws SQLException {
        return SQLUtil.execute("UPDATE Item SET name = ?, qty = ?,price= ?, description=?  WHERE ItemId = ?", dto.getName(), dto.getQty(),dto.getPrice(),dto.getDescription(),dto.getItemId() );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Item WHERE ItemId = ?", id);
    }

    @Override
    public Item search(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE ItemId = ?", id + "");
        rst.next();
        return new Item(id + "", rst.getString("name"), rst.getInt("qty"), rst.getDouble("price"), rst.getString("description"));
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }


    @Override
    public boolean updateSet(List<OrderDetails> orderDetailsList) {
        return false;
    }
}
package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.ItemDAO;
import lk.ijse.entity.ItemDTO;
import lk.ijse.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        ArrayList<Item> all = (ArrayList<Item>) itemDAO.getAll();
        for (Item i : all) {
            allItem.add(new ItemDTO(i.getItemId(), i.getName(), i.getQty(), i.getPrice(),i.getDescription()));
        }
        return allItem;
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException {
        return itemDAO.save(new Item(dto.getItemId(), dto.getName(), dto.getQty(), dto.getPrice(),dto.getDescription()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getItemId(), dto.getName(), dto.getQty(), dto.getPrice(),dto.getDescription()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public Item searchItem(String id) throws SQLException {
        return itemDAO.search(id);
    }

}









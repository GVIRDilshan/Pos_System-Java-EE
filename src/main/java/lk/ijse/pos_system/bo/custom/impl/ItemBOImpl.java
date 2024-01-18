package lk.ijse.pos_system.bo.custom.impl;

import lk.ijse.pos_system.bo.custom.ItemBO;
import lk.ijse.pos_system.dao.DAOFactory;
import lk.ijse.pos_system.dao.custom.ItemDAO;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        itemDAO.add(modelMapper.map(itemDTO, Item.class));
        return true;

    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        Item item = itemDAO.search(id);
        return modelMapper.map(item, ItemDTO.class);

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        itemDAO.update(modelMapper.map(itemDTO, Item.class));
        return true;

    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        itemDAO.delete(id);
        return true;

    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
            List<Item> itemList = itemDAO.getAll();

            List<ItemDTO> itemDTOList = new ArrayList<>();

            for (Item item : itemList) {
                itemDTOList.add(modelMapper.map(item, ItemDTO.class));

            }

            return itemDTOList;

    }

}

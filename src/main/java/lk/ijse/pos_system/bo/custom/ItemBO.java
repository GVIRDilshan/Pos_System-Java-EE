package lk.ijse.pos_system.bo.custom;

import lk.ijse.pos_system.bo.SuperBO;
import lk.ijse.pos_system.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO itemDTO) throws Exception;
    ItemDTO searchItem(String id) throws Exception;
    boolean updateItem(ItemDTO itemDTO) throws Exception;
    boolean deleteItem(String id) throws Exception;
    List<ItemDTO> getAllItems() throws Exception;

}

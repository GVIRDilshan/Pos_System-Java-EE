package lk.ijse.pos_system.bo.custom;

import lk.ijse.pos_system.bo.SuperBO;
import lk.ijse.pos_system.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    boolean addOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO searchOrder(String id) throws Exception;
    List<OrderDTO> getAllOrders() throws Exception;

}

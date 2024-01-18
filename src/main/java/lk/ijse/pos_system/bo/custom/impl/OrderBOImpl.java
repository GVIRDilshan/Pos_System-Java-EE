package lk.ijse.pos_system.bo.custom.impl;

import lk.ijse.pos_system.bo.custom.OrderBO;
import lk.ijse.pos_system.dao.DAOFactory;
import lk.ijse.pos_system.dao.custom.CustomerDAO;
import lk.ijse.pos_system.dao.custom.ItemDAO;
import lk.ijse.pos_system.dao.custom.OrderDAO;
import lk.ijse.pos_system.dao.custom.OrdersItemDAO;
import lk.ijse.pos_system.db.DBConnection;
import lk.ijse.pos_system.dto.CustomerDTO;
import lk.ijse.pos_system.dto.ItemDTO;
import lk.ijse.pos_system.dto.OrderDTO;
import lk.ijse.pos_system.entity.Item;
import lk.ijse.pos_system.entity.Orders;
import org.modelmapper.ModelMapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private OrdersItemDAO ordersItemDAO = (OrdersItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERS_ITEM);
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addOrder(OrderDTO orderDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);
        try {
            for (ItemDTO itemDTO : orderDTO.getItems()) {
                Item tempItem = itemDAO.search(itemDTO.getiCode());

                int newQty = tempItem.getIQty() - itemDTO.getiQty();

                if (newQty < 0) {
                    throw new Exception();
                }

                tempItem.setIQty(newQty);

                itemDAO.update(tempItem);

            }

            Orders orders = modelMapper.map(orderDTO, Orders.class);

            orderDAO.add(orders);

            ordersItemDAO.add(orders);

            connection.commit();
            return true;

        } catch (Exception e) {
            throw new Exception();
        } finally {
            connection.rollback();
            connection.setAutoCommit(true);
        }

    }

    @Override
    public OrderDTO searchOrder(String id) throws Exception {
        Orders orders = orderDAO.search(id);
        OrderDTO orderDTO = modelMapper.map(orders, OrderDTO.class);

        orders.setItems(ordersItemDAO.search(id).getItems());
        orders.setCustomer(customerDAO.search(orders.getCustomer().getcId()));

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : orders.getItems()) {
            itemDTOList.add(
                    new ItemDTO(
                            item.getICode(),
                            item.getIName(),
                            item.getIPrice(),
                            item.getIQty()
                    )
            );
        }

        orderDTO.setItems(itemDTOList);
        orderDTO.setCustomerDTO(modelMapper.map(orders.getCustomer(), CustomerDTO.class));

        return orderDTO;

    }

    @Override
    public List<OrderDTO> getAllOrders() throws Exception {
        List<Orders> ordersList = orderDAO.getAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Orders orders : ordersList) {
            orderDTOList.add(searchOrder(orders.getOId()));

        }

        return orderDTOList;

    }

}

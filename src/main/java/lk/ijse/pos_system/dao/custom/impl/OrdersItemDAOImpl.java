package lk.ijse.pos_system.dao.custom.impl;

import lk.ijse.pos_system.dao.custom.OrdersItemDAO;
import lk.ijse.pos_system.entity.Item;
import lk.ijse.pos_system.entity.Orders;
import lk.ijse.pos_system.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdersItemDAOImpl implements OrdersItemDAO {

    @Override
    public boolean add(Orders orders) throws Exception {
        String sql = "INSERT INTO orders_item VALUES (? , ? , ?)";

        List<Item> items = orders.getItems();


        for (Item item : items) {
            boolean isAdded = CrudUtil.execute(
                    sql,
                    orders.getOId(),
                    item.getICode(),
                    item.getIQty()
            );

            if (!isAdded) {
                throw new Exception();
            }
        }

        return true;

    }

    @Override
    public Orders search(String id) throws Exception {
        String sql = "SELECT orders_item.oId , orders_item.iCode , orders_item.qty , item.iName , item.iPrice FROM" +
                " orders_item INNER JOIN item ON orders_item.iCode = item.iCode WHERE oid = ?";

        ResultSet rs = CrudUtil.execute(sql, id);

        List<Item> items = new ArrayList<>();

        while (rs.next()) {
            items.add(
                    new Item(
                            rs.getString(2),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getInt(3)
                    )
            );

        }

        return new Orders(
                id,
                null,
                0,
                0,
                0,
                0,
                null,
                items
        );

    }

    @Override
    public boolean update(Orders orders) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<Orders> getAll() throws Exception {
        return null;
    }
}

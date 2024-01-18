package lk.ijse.pos_system.dao.custom.impl;

import lk.ijse.pos_system.dao.custom.OrderDAO;
import lk.ijse.pos_system.entity.Customer;
import lk.ijse.pos_system.entity.Orders;
import lk.ijse.pos_system.util.CrudUtil;
import org.hibernate.query.NativeQuery;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders order) throws Exception {
        String sql = "INSERT INTO orders VALUES(? , ? , ? , ? , ? , ? , ?)";

        boolean isAdded = CrudUtil.execute(
                sql,
                order.getOId(),
                order.getODate(),
                order.getOTotal(),
                order.getOSubTotal(),
                order.getODiscount(),
                order.getOBalance(),
                order.getCustomer().getcId()
        );

        if (isAdded) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public Orders search(String id) throws Exception {
        String sql = "SELECT * FROM orders WHERE oId = ?";

        ResultSet rs = CrudUtil.execute(sql, id);

        if (rs.next()) {
            Customer customer = new Customer();
            customer.setcId(rs.getString(7));

            return new Orders(
                    rs.getString(1),
                    rs.getDate(2),
                    rs.getDouble(3),
                    rs.getDouble(4),
                    rs.getInt(5),
                    rs.getDouble(6),
                    customer,
                    null
            );
        }

        throw new Exception();

    }

    @Override
    public boolean update(Orders order) throws Exception {
        String sql = "UPDATE orders SET oId = ? , oDate = ? , oTotal = ? , oSubTotal = ? , oDiscount = ? , " +
                "oBalance = ? , cid = ?";

        boolean isUpdated = CrudUtil.execute(
                sql,
                order.getOId(),
                order.getODate(),
                order.getOTotal(),
                order.getOSubTotal(),
                order.getODiscount(),
                order.getOBalance(),
                order.getCustomer().getcId()
        );

        if (isUpdated) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM orders WHERE oId = ?";

        boolean isDeleted = CrudUtil.execute(sql, id);

        if (isDeleted) {
            return true;
        }
        throw new Exception();

    }

    @Override
    public List<Orders> getAll() throws Exception {
        String sql = "SELECT * FROM orders";

        ResultSet rs = CrudUtil.execute(sql);

        List<Orders> ordersList = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setcId(rs.getString(7));

            ordersList.add(
                    new Orders(
                            rs.getString(1),
                            rs.getDate(2),
                            rs.getDouble(3),
                            rs.getDouble(4),
                            rs.getInt(5),
                            rs.getDouble(6),
                            customer,
                            null
                    )
            );
        }
        return ordersList;

    }

}

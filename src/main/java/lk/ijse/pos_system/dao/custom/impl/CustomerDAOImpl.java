package lk.ijse.pos_system.dao.custom.impl;

import lk.ijse.pos_system.dao.custom.CustomerDAO;
import lk.ijse.pos_system.entity.Customer;
import lk.ijse.pos_system.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws Exception {
        String sql = "INSERT INTO customer VALUES (? , ? , ? , ?)";

        boolean isAdded = CrudUtil.execute(
                sql ,
                customer.getcId() ,
                customer.getcName() ,
                customer.getcAddress() ,
                customer.getcSalary()
        );

        if (isAdded) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public Customer search(String id) throws Exception {
        String sql = "SELECT * FROM customer WHERE cId = ?";

        ResultSet rs = CrudUtil.execute(sql, id);

        if (rs.next()){
            return new Customer(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)
            );
        }

        throw new Exception();

    }

    @Override
    public boolean update(Customer customer) throws Exception {
        String sql = "UPDATE customer SET cName = ? , cAddress = ? , cSalary = ? WHERE cId = ?";

        boolean isUpdated = CrudUtil.execute(
                sql ,
                customer.getcName() ,
                customer.getcAddress() ,
                customer.getcSalary() ,
                customer.getcId()
        );

        if (isUpdated) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM customer WHERE cId = ?";

        boolean isDeleted = CrudUtil.execute(sql , id);

        if (isDeleted) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public List<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM customer";

        ResultSet rs = CrudUtil.execute(sql);
        List<Customer> customers = new ArrayList<>();

        while (rs.next()) {
            customers.add(
                    new Customer(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4)
                    )
            );

        }

        return customers;

    }

}

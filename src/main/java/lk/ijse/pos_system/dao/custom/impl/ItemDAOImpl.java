package lk.ijse.pos_system.dao.custom.impl;

import lk.ijse.pos_system.dao.custom.ItemDAO;
import lk.ijse.pos_system.entity.Item;
import lk.ijse.pos_system.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws Exception {
        String sql = "INSERT INTO item VALUES (? , ? , ? , ?)";

        boolean isAdded =  CrudUtil.execute(
                sql ,
                item.getICode() ,
                item.getIName() ,
                item.getIPrice() ,
                item.getIQty()
        );

        if (isAdded){
            return true;
        }
        throw new Exception();

    }

    @Override
    public Item search(String id) throws Exception {
        String sql = "SELECT * FROM item WHERE iCode = ?";

        ResultSet rs = CrudUtil.execute(sql, id);

        if (rs.next()) {
            return new Item(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4)
            );
        }

        throw new Exception();

    }

    @Override
    public boolean update(Item item) throws Exception {
        String sql = "UPDATE item SET iName = ? , iPrice = ? , iQty = ? WHERE iCode = ?";

        boolean isUpdated = CrudUtil.execute(
                sql ,
                item.getIName() ,
                item.getIPrice() ,
                item.getIQty() ,
                item.getICode()
        );

        if (isUpdated) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM Item WHERE iCode = ?";

        boolean isDeleted = CrudUtil.execute(sql , id);

        if (isDeleted) {
            return true;
        }

        throw new Exception();

    }

    @Override
    public List<Item> getAll() throws Exception {
        String sql = "SELECT * FROM item";

        ResultSet rs = CrudUtil.execute(sql);

        List<Item> items = new ArrayList<>();

        while (rs.next()) {
            items.add(
                    new Item(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getInt(4)
                    )
            );
        }

        return items;
    }

}

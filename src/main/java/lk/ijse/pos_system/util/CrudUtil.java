package lk.ijse.pos_system.util;

import lk.ijse.pos_system.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql , Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for(int i = 0 ; i < args.length ; i++ ){
            ps.setObject((i+1) , args[i]);
        }

        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) ps.executeQuery();
        }else {
            return (T)(Boolean) (ps.executeUpdate() > 0);
        }

    }

}

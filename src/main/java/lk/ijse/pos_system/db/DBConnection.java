package lk.ijse.pos_system.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/pos";
    private final static String user = "root";
    private final static String password = "1234";

    private static DBConnection dbConnection;
    private Connection con;

    private DBConnection() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        con = DriverManager.getConnection(URL , user , password);

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");

            con = ds.getConnection();

        } catch (NamingException e) {
//            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            return dbConnection = new DBConnection();
        } else {
            return dbConnection;
        }

    }

    public Connection getConnection() {
        return con;
    }

}

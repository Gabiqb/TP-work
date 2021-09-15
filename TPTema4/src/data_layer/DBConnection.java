package data_layer;

import model.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection c;

    public static Connection getC() {
        return c;
    }

    public DBConnection()
    {
        try {
            c=DriverManager.getConnection(Model.getURL(), Model.getUSER(), Model.getPASSWORD());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuldt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Le Duy Tuan Vu
 */
public class ConnectionDB {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connect = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment3_LeDuyTuanVu";
            connect = DriverManager.getConnection(url, "sa", "030798");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connect;
    }
}

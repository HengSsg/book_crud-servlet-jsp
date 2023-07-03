package util;

import java.sql.*;

public class ConnectionManager2 {
    private static volatile ConnectionManager2 connectionManager2;
    private Connection conn;

    public ConnectionManager2() {


        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306";
            String driver = "com.mysql.cj.jdbc.Driver";
            String id = "root";
            String pw = "gkgkgk12";

            Class.forName(driver);

            this.conn = DriverManager.getConnection(jdbcUrl, id, pw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager2 getInstance() {
        if (connectionManager2 == null) {
            synchronized (ConnectionManager2.class) {
                connectionManager2 = new ConnectionManager2();

            }
        }
        return connectionManager2;
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//package util;
//
//import java.sql.*;
//
//public class ConnectionManager2 {
//    private static ConnectionManager2 manager;
//    private Connection conn;
//
//    private ConnectionManager2() {
//        try {
//            String driver = "com.mysql.cj.jdbc.Driver";
//            String jdbcUrl = "jdbc:mysql://localhost:3306/book_crud";
//            String id = "root";
//            String pw = "gkgkgk12";
//
//            Class.forName(driver);
//            conn = DriverManager.getConnection(jdbcUrl, id, pw);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ConnectionManager2 getInstance() {
//        if (manager == null) {
//            synchronized (ConnectionManager2.class) {
//                if (manager == null) {
//                    manager = new ConnectionManager2();
//                }
//            }
//        }
//        return manager;
//    }
//
//    public Connection getConnection() {
//        return conn;
//    }
//
//    public void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

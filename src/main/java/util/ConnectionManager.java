package util;

import java.sql.*;

public class ConnectionManager {
    private static volatile ConnectionManager manager;
    private Connection conn;

    // 생성할때 커넥션 생성
    private ConnectionManager() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String jdbcUrl = "jdbc:mysql://localhost:3306/book_crud";
            String id = "root";
            String pw = "gkgkgk12";

            Class.forName(driver);
            conn = DriverManager.getConnection(jdbcUrl, id, pw);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("커넥션 생성 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }

    // 인스턴스가 null이면 생성
    public static ConnectionManager getInstance() {
        if (manager == null) {
            synchronized (ConnectionManager.class) {
                if (manager == null) {
                    manager = new ConnectionManager();
                }
            }
        }

        return manager;
    }

    // 커넥션 반환
    public Connection getConnection() {
        return conn;
    }

    // 리소스 반환
    public void closeConnection(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

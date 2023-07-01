package dao;

import model.vo.Book;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    private final ConnectionManager connectionManager;
    private final Connection conn;

    public BookDAO() {
        connectionManager = ConnectionManager.getInstance();
        conn = connectionManager.getConnection();
    }

    // 책 하나 select
    public Book selectOneBook(int no) {
        Book book = new Book();
        String sql = "select * from book where no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                book.setNo(rs.getInt("no"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
                book.setUpdatedAt(rs.getTimestamp("updated_at"));
            }
            connectionManager.closeConnection(rs, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // 책 전체 select
    public ArrayList<Book> selectAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "select * from book";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Book book = new Book();
                book.setNo(rs.getInt("no"));
                book.setBookName(rs.getString("book_name"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookPublisher(rs.getString("book_publisher"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
                book.setUpdatedAt(rs.getTimestamp("updated_at"));
                books.add(book);
            }
            connectionManager.closeConnection(rs, stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // 책 정보 업데이트
    public boolean updateOneBook(Book book) {
        int affectedNum = 0;
        String sql = "update book set book_name = ?, book_author = ?, book_publisher = ?, updated_at = ? where no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookAuthor());
            pstmt.setString(3, book.getBookPublisher());
            pstmt.setTimestamp(4, book.getUpdatedAt());
            pstmt.setInt(5, book.getNo());

            affectedNum = pstmt.executeUpdate();

            connectionManager.closeConnection(null, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedNum > 0;
    }

    // 책 삭제
    public boolean deleteOneBook(int no) {
        int affectedNum = 0;
        String sql = "delete from book where no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);

            affectedNum = pstmt.executeUpdate();

            connectionManager.closeConnection(null, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedNum > 0;
    }
}

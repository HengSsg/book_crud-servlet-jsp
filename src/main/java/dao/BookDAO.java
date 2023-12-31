package dao;

import model.vo.Book;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO implements BookRepository {
    private final ConnectionManager connectionManager;
    private final Connection conn;

    public BookDAO() {
        connectionManager = ConnectionManager.getInstance();
        conn = connectionManager.getConnection();
    }

    // 책 추가
    @Override
    public boolean insertOne(Book book) {
        int affectedNum = 0;
        String sql = "insert into \n" +
                "book(book_name, book_author, book_publisher) \n" +
                "values(?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookAuthor());
            pstmt.setString(3, book.getBookPublisher());

            affectedNum = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedNum > 0;
    }

    // 책 하나 select
    @Override
    public Book selectOne(int no) {
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
    @Override
    public ArrayList<Book> selectAll() {
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
    @Override
    public boolean updateOne(Book book) {
        int affectedNum = 0;
        String sql = "update book set book_name = ?, book_author = ?, book_publisher = ?, updated_at = now() where no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getBookAuthor());
            pstmt.setString(3, book.getBookPublisher());
            pstmt.setInt(4, book.getNo());

            affectedNum = pstmt.executeUpdate();

            connectionManager.closeConnection(null, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedNum > 0;
    }

    // 책 삭제
    @Override
    public boolean deleteOne(int no) {
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

    @Override
    public int selectAllCount() {
        int totalNum = 0;
        String sql = "SELECT COUNT(*) AS total FROM book";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                totalNum = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalNum;
    }

    @Override
    public ArrayList<Book> selectPages(int skipNum, int pageSize) {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "select * from book ORDER BY created_at DESC limit ?, ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, skipNum);
            pstmt.setInt(2, pageSize);
            ResultSet rs = pstmt.executeQuery();

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
            connectionManager.closeConnection(rs, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


}

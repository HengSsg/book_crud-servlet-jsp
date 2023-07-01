package dao;

import model.vo.Book;

import java.util.ArrayList;

public interface BookRepository {
    Book selectOne(int no);

    ArrayList<Book> selectAll();

    boolean updateOne(Book book);

    boolean deleteOne(int no);
}

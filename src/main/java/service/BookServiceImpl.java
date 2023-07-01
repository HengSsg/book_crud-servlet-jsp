package service;

import dao.BookDAO;
import dao.BookRepository;
import model.vo.Book;

import java.util.ArrayList;

public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = new BookDAO();
    }

    @Override
    public boolean createBook() {
//        bookRepository.insertOne();
    }

    @Override
    public Book getOneBook(int no) {
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return null;
    }

    @Override
    public boolean updateOneBook() {
        return false;
    }

    @Override
    public boolean deleteOneBook(int no) {
        return false;
    }
}

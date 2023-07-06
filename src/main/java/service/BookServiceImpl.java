package service;

import dao.BookDAO;
import dao.BookRepository;
import model.vo.Book;

import java.util.ArrayList;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = new BookDAO();
    }

    @Override
    public boolean createBook(Book book) {
        return bookRepository.insertOne(book);
    }

    @Override
    public Book getOneBook(int no) {
        return bookRepository.selectOne(no);
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return bookRepository.selectAll();
    }

    @Override
    public boolean updateOneBook(Book book) {
        return bookRepository.updateOne(book);
    }

    @Override
    public boolean deleteOneBook(int no) {
        return bookRepository.deleteOne(no);
    }

    @Override
    public int getAllBooksNum() {
        return  bookRepository.selectAllCount();
    }

    @Override
    public ArrayList<Book> getPages(int skipSize, int pageSize) {
        return bookRepository.selectPages(skipSize, pageSize);
    }


}

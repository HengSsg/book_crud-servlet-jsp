package service;

import model.vo.Book;

import java.util.ArrayList;

public interface BookService {

    boolean createBook(Book book); // 책 하나 추가하기

    Book getOneBook(int no); // 책 하나 가져오기

    ArrayList<Book> getAllBooks(); // 전체 리스트 가져오기

    boolean updateOneBook(Book book); // 책 하나의 정보 수정하기

    boolean deleteOneBook(int no); // 책 하나 삭제하기

    int getAllBooksNum();

    ArrayList<Book> getPages(int skipSize, int pageSize);
}

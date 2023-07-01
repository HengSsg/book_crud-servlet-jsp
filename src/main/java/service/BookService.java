package service;

import model.vo.Book;

import java.util.ArrayList;

public interface BookService {

    // 책 하나 가져오기
    Book getOneBook(int no);

    // 전체 리스트 가져오기
    ArrayList<Book> getAllBooks();

    // 책 하나의 정보 수정하기
    boolean updateOneBook();

    // 책 하나 삭제하기
    boolean deleteOneBook(int no);

}

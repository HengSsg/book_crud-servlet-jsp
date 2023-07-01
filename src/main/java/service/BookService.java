package service;

import model.vo.Book;

import java.util.ArrayList;

public interface BookService {

    boolean createBook(); // 책 하나 추가하기

    Book getOneBook(int no); // 책 하나 가져오기

    ArrayList<Book> getAllBooks(); // 전체 리스트 가져오기

    boolean updateOneBook(); // 책 하나의 정보 수정하기

    boolean deleteOneBook(int no); // 책 하나 삭제하기

}

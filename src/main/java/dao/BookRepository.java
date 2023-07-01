package dao;

import model.vo.Book;

import java.util.ArrayList;

public interface BookRepository {
    boolean insertOne(Book book); // 책 정보 추가
    Book selectOne(int no); // 책 데이터 하나 조회

    ArrayList<Book> selectAll(); // 책 데이터 전체 조회

    boolean updateOne(Book book); // 책 데이터 수정

    boolean deleteOne(int no); // 책 데이터 삭제
}

-- 데이터베이스 생성 
create database book_crud;

-- use 
use book_crud;
-- 테이블 생성 
create table book(
	no int auto_increment primary key ,
    book_name varchar(200) not null,
    book_author varchar(200) not null,
    book_publisher varchar(200) not null,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
    
    );
-- 테이블 삭제 
DROP TABLE book;
-- 모든 데이터 조회
select * from book;
-- 하나의 데이터 조회
select * from book where no = 1;
-- book 데이터 삽입
insert into 
book(book_name, book_author, book_publisher) 
values("사피엔스3", "호모", "출판사");
-- book 데이터 수정
update book
set book_name = "호모사피엔스1", book_author = "박형석작가", book_publisher = "박형석 출판사"
where no = 1;
-- book 데이터 삭제
delete from book
where no = 1;
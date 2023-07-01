<%@page import="model.vo.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Book Details</title>
    <style>
      body {
        font-family: "Noto Sans", sans-serif;
        background-color: #f6f6f6;
        padding: 20px;
      }

      .container {
        max-width: 600px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
      }

      h2 {
        margin-bottom: 20px;
        border-bottom: 1px solid #eaeaea;
        padding-bottom: 10px;
        color: #222;
      }

      p {
        margin-bottom: 10px;
        color: #444;
      }

      .btn-container {
        margin-top: 20px;
      }

      .btn {
        display: inline-block;
        padding: 8px 16px;
        border-radius: 4px;
        background-color: #19baff;
        color: #fff;
        text-decoration: none;
        margin-right: 10px;
        font-weight: bold;
        transition: background-color 0.3s;
      }

      .btn:hover {
        background-color: #00a1e0;
      }
    </style>
  </head>
  <body>
  <%Book book = (Book)request.getAttribute("book"); %>
    <div class="container">
      <h2>도서 상세 정보</h2>

      <p>도서 번호: <span id="bookNo"><%=book.getNo() %></span></p>
      <p>도서명: <span id="bookName"><%=book.getBookName() %></span></p>
      <p>저자: <span id="bookAuthor"><%=book.getBookAuthor() %></span></p>
      <p>
        출판사: <span id="bookPublisher"><%=book.getBookPublisher() %></span>
      </p>
      <p>생성일: <span id="createdAt"><%=book.getCreatedAt() %></span></p>
      <p>수정일: <span id="updatedAt"><%=book.getUpdatedAt() %></span></p>

      <div class="btn-container">
       <a href="/book_crud/books/list" class="btn">뒤로가기</a>
        <a href="/book_crud/books/update/<%=book.getNo() %>" class="btn"
          >수정하기</a
        >
       <a href="#" class="btn" onclick="deleteBook(<%=book.getNo() %>)">삭제하기</a>
      </div>
    </div>
	  <script>
	  function deleteBook(bookNo) {
	        var xhr = new XMLHttpRequest();
	        xhr.open("DELETE", "/book_crud/books/delete/" + bookNo, true);
	        xhr.onreadystatechange = function () {
	          if (xhr.readyState === 4 && xhr.status === 200) {
	            // 요청이 성공적으로 처리되었을 때 실행할 코드
	            alert("도서가 삭제되었습니다.");
	            window.location = "/book_crud/books/list";
	            // 페이지를 새로고침하거나 동적으로 삭제된 도서를 제거하는 등의 작업을 수행할 수 있습니다.
	          }
	        };
	        xhr.send();
	      }
	  </script>
  </body>
</html>

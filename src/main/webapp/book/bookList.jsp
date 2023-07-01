<%@page import="model.vo.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookList</title>
    <style>
      body {
        font-family: "Noto Sans", sans-serif;
        background-color: #f6f6f6;
        padding: 20px;
      }

      table {
        width: 900px;
        margin: 0 auto;
        background-color: #fff;
        border-collapse: collapse;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
      }

      th,
      td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ccc;
      }

      th {
        background-color: #f2f2f2;
      }
    </style>
  </head>
  <body>
  <%
 ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
  %>
    <table>
      <tr>
        <th>책 번호</th>
        <th>도서명</th>
        <th>저자</th>
        <th>출판사</th>
        <th></th>
      </tr>
      <% for (Book book:books) {%>
      <tr>
        <td><%=book.getNo() %></td>
        <td><a href="list/<%=book.getNo() %>"><%=book.getBookName() %></a></td>
        <td><%=book.getBookAuthor() %></td>
        <td><%=book.getBookPublisher() %></td>
         <td><button class="btn" onclick="deleteBook(<%=book.getNo() %>)">삭제하기</button></td>
      </tr>
           <% }%>
     
      <!-- Add more rows for each book in the list -->
    </table>
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

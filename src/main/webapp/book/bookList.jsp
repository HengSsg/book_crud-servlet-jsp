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
        <th>생성일</th>
        <th>수정일</th>
      </tr>
      <% for (Book book:books) {%>
      <tr>
        <td><%=book.getNo() %></td>
        <td><a href="list/<%=book.getNo() %>"><%=book.getBookName() %></a></td>
        <td><%=book.getBookAuthor() %></td>
        <td><%=book.getBookPublisher() %></td>
        <td><button>삭제</button></td>
      </tr>
           <% }%>
     
      <!-- Add more rows for each book in the list -->
    </table>
  </body>
</html>

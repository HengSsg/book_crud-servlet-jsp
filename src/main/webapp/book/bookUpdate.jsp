<%@page import="model.vo.Book"%> <%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Book Update</title>
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

      form {
        margin-bottom: 10px;
      }

      label {
        display: block;
        margin-bottom: 5px;
        color: #444;
      }

      input[type="text"] {
        width: 100%;
        padding: 8px;
        border-radius: 4px;
        border: 1px solid #ccc;
        margin-bottom: 10px;
      }

      .btn-container {
        text-align: right;
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <body>
    <%Book book = (Book)request.getAttribute("book"); %>
    <div class="container">
      <h2>도서 수정</h2>

      <form id="myForm">
        <input type="hidden" name="bookNo" value="<%=book.getNo() %>" />
        <label for="bookName">도서명</label>
        <input
          type="text"
          id="bookName"
          name="bookName"
          value="<%=book.getBookName() %>"
          required
        />
        <label for="bookAuthor">저자</label>
        <input
          type="text"
          id="bookAuthor"
          name="bookAuthor"
          value="<%=book.getBookAuthor() %>"
          required
        />
        <label for="bookPublisher">출판사</label>
        <input
          type="text"
          id="bookPublisher"
          name="bookPublisher"
          value="<%=book.getBookPublisher() %>"
          required
        />

        <div class="btn-container">
          <button type="submit">수</button>
          <a href="/book_crud/books/list/<%=book.getNo() %>" class="btn"
            >취소</a
          >
        </div>
      </form>
    </div>
    <script>
      $(function () {
        $("#myForm").submit(function (e) {
          e.preventDefault();

          let formData = $(this).serializeArray();
          let jsonData = formData.reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
          }, {});
          let data = JSON.stringify(jsonData);

          console.log(jsonData.bookNo);

          let url1 = "/book_crud/books/update/" + jsonData.bookNo;
          let url2 = "/book_crud/books/list/" + jsonData.bookNo;

          $.ajax({
            url: url1,
            type: "PUT",
            data: data,
            success: function (response) {
              // handle success response
              console.log("수정되었습니다.");
              alert("수정되었습니다.");
              window.location = url2;
            },
            error: function (xhr, status, error) {
              // handle error response
              console.log("수정에 실패하였습니다.");
            },
          });
        });
      });
    </script>
  </body>
</html>

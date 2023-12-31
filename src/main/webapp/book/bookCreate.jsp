<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>bookCreate</title>
    <style>
      body {
        font-family: "Noto Sans", sans-serif;
        background-color: #f6f6f6;
        padding: 20px;
      }

      form {
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
      }

      label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
      }

      input[type="text"] {
        width: 95%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
        font-size: 14px;
        margin-bottom: 20px;
      }

      input[type="submit"] {
        background-color: #00a1e0;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
        font-size: 14px;
      }

      input[type="submit"]:hover {
        background-color: #00a1e0;
      }
    </style>
  </head>
  <body>
    <form action="/book_crud/books/create" method="POST">
      <h2>도서 생성</h2>

      <label for="bookName">도서 이름:</label>
      <input type="text" id="bookName" name="bookName" required />

      <label for="bookAuthor">저자:</label>
      <input type="text" id="bookAuthor" name="bookAuthor" required />

      <label for="bookPublisher">출판사:</label>
      <input type="text" id="bookPublisher" name="bookPublisher" required />

      <input type="submit" value="Submit" />
    </form>
  </body>
</html>

<%@ page import="util.Paging" %>
<%@ page import="model.vo.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
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

        .pagination {
            justify-content: center;
        }

        .pagination .page-item .page-link {
            color: #000;
            background-color: #f2f2f2;
            border-color: #ccc;
        }

        .pagination .page-item .page-link:hover {
            background-color: #e6e6e6;
        }

        .pagination .page-item.active .page-link {
            z-index: 1;
            color: #fff;
            background-color: #19baff;
            border-color: #19baff;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <%
    ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
    Paging paging = (Paging) request.getAttribute("paging");

    int currentPage = paging.getCurrentPage();
    int totalPage = paging.getTotalPage();
    int startPage = Math.max(currentPage - 5, 1);
    int endPage = Math.min(currentPage + 5, totalPage);

    %>
    <table>
        <tr>
            <th>책 번호</th>
            <th>도서명</th>
            <th>저자</th>
            <th>출판사</th>
            <th></th>
        </tr>
        <% for (Book book : books) { %>
        <tr>
            <td><%= book.getNo() %></td>
            <td><a href="list/<%= book.getNo() %>"><%= book.getBookName() %></a></td>
            <td><%= book.getBookAuthor() %></td>
            <td><%= book.getBookPublisher() %></td>
            <td><a href="#" class="btn deleteBtn"
                data-book-no="<%= book.getNo() %>">삭제하기</a></td>
        </tr>
        <% } %>
        <tr>
            <td colspan="5">
                <!-- 페이지 번호 출력 -->
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <% if (startPage > 1) { %>
                            <li class="page-item">
                                <a class="page-link" href="?page=1&pageSize=<%= paging.getPageSize() %>">&laquo;</a>
                            </li>
                        <% } %>
                        <% for (int i = startPage; i <= endPage; i++) { %>
                            <li class="page-item <%= i == currentPage ? "active" : "" %>">
                                <a class="page-link" href="?page=<%= i %>&pageSize=<%= paging.getPageSize() %>"><%= i %></a>
                            </li>
                        <% } %>
                        <% if (endPage < totalPage) { %>
                            <li class="page-item">
                                <a class="page-link" href="?page=<%= totalPage %>&pageSize=<%= paging.getPageSize() %>">&raquo;</a>
                            </li>
                        <% } %>
                    </ul>
                </nav>
            </td>
        </tr>
        <tr>
            <td><a href="#" class="btn" id="createBtn">도서 생성하기</a></td>
        </tr>
        <!-- Add more rows for each book in the list -->
    </table>

    <script>
        $(".deleteBtn").click(function(e) {
            var bookNo = $(this).data("book-no");

            $.ajax({
                url: "/book_crud/books/delete/" + bookNo,
                type: "DELETE",
                success: function(response) {
                    alert("도서가 삭제되었습니다.");
                   window.location.reload();
                },
                error: function(xhr, status, error) {
                    console.log("도서 삭제에 실패하였습니다.");
                },
            });
        });

        $("#createBtn").click(function() {
            window.location = "/book_crud/books/create";
        })
    </script>
</body>
</html>

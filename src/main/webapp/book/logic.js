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
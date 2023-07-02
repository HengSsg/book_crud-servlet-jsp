package controller;

import model.vo.Book;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.BookService;
import service.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private final BookService bookService;

    public BookServlet() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String endpoint = getEndpoint(req.getPathInfo());

        if ("list".equals(endpoint)) { // 전체 책 리스트 조회
            String id = getId(req.getPathInfo());
            if (id != null) { // no에 해당하는 book상세 리턴
                Book book = bookService.getOneBook(Integer.parseInt(id));

                req.setAttribute("book", book);
                RequestDispatcher rd = req.getRequestDispatcher("/book/bookDetail.jsp");
                rd.forward(req, resp);

            } else { // no 가 없으면 전제 리스트 조회
                ArrayList<Book> books = bookService.getAllBooks();

                req.setAttribute("books", books);
                RequestDispatcher rd = req.getRequestDispatcher("/book/bookList.jsp");
                rd.forward(req, resp);

            }
        }
        if ("create".equals(endpoint)) { // 책 생성 페이지 리턴

            RequestDispatcher rd = req.getRequestDispatcher("/book/bookCreate.jsp");
            rd.forward(req, resp);
        }
        if ("update".equals(endpoint)) {
            String id = getId(req.getPathInfo());

            if (id != null) { // no에 해당하는 book상세 리턴
                Book book = bookService.getOneBook(Integer.parseInt(id));

                req.setAttribute("book", book);
                RequestDispatcher rd = req.getRequestDispatcher("/book/bookUpdate.jsp");
                rd.forward(req, resp);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String endpoint = getEndpoint(req.getPathInfo());

        if ("create".equals(endpoint)) { // 책 데이터 생성 로직
            String bookName = req.getParameter("bookName");
            String bookAuthor = req.getParameter("bookAuthor");
            String bookPublisher = req.getParameter("bookPublisher");

            Book book = new Book();
            book.setBookName(bookName);
            book.setBookAuthor(bookAuthor);
            book.setBookPublisher(bookPublisher);

            bookService.createBook(book);

            System.out.println("도서 1개가 생성되었습니다.");
            resp.sendRedirect(req.getContextPath() + "/books/list");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String endpoint = getEndpoint(req.getPathInfo());

        if ("update".equals(endpoint)) { // 책 정보 수정 로직
            String id = getId(req.getPathInfo());

            if (id != null) { // no에 해당하는 book 수정
                Book book = new Book();

                StringBuilder requestBody = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
                reader.close();

                String bodyContent = requestBody.toString();
                JSONParser parser = new JSONParser();
                Object obj;
                JSONObject jsonObj = null;
                try {
                    obj = parser.parse(bodyContent);
                    jsonObj = (JSONObject) obj;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                book.setNo(Integer.parseInt(jsonObj.get("bookNo").toString()));
                book.setBookName(jsonObj.get("bookName").toString());
                book.setBookAuthor(jsonObj.get("bookAuthor").toString());
                book.setBookPublisher(jsonObj.get("bookPublisher").toString());

                boolean flag = bookService.updateOneBook(book);
                if(flag) {
                    System.out.println("수정이 완료되었습니다.");
                } else {
                    System.out.println("수정에 실패했습니다.");
                }
            }
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String endpoint = getEndpoint(req.getPathInfo());

        if ("delete".equals(endpoint)) { // 책 삭제 로직
            String id = getId(req.getPathInfo());
            if (id != null) { // no에 해당하는 book 삭제
                System.out.println(id);
                boolean flag = bookService.deleteOneBook(Integer.parseInt(id));

                if (flag) {
                    System.out.println("책이 삭제 되었습니다!!");
                } else {
                    System.out.println("책 삭제에 실패했습니다.");
                }
            }
        }
    }

    private String getEndpoint(String pathInfo) {
        String[] split = pathInfo.split("/");
        if (split.length > 1) {
            return split[1];
        }
        return null;
    }

    private String getId(String pathInfo) {
        String[] split = pathInfo.split("/");
        if (split.length > 2) {
            return split[2];
        }
        return null;
    }
}

package controller;

import service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet extends HttpServlet {
    private final BookServiceImpl bookService;

    public BookServlet() {
        this.bookService = new BookServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = getEndpoint(req.getPathInfo());

        if ("list".equals(endpoint)) { // 전체 책 리스트 조회
            String id = getId(req.getPathInfo());
            if (id != null) { // no에 해당하는 book상세 리턴
                resp.getWriter().write(id);

            } else { // no 가 없으면 전제 리스트 조회
                resp.getWriter().write(endpoint);

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = getEndpoint(req.getPathInfo());

        if ("create".equals(endpoint)) { // 책 데이터 생성 로직

        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = getEndpoint(req.getPathInfo());

        if ("update".equals(endpoint)) { // 책 정보 수정 로직

        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String endpoint = getEndpoint(req.getPathInfo());

        if ("delete".equals(endpoint)) { // 책 삭제 로직

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

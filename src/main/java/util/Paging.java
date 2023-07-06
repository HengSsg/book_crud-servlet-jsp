package util;

public class Paging {
    private int totalPage;
    private int currentPage;
    private int pageSize;


    public Paging(int totalPage, int currentPage, int displayNum) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = displayNum;
    }


    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

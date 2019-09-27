package com.qf.pojo;

import java.util.List;

/**
 * @author zyh
 * @date 2019/9/10 0010 21:53
 * 佛祖保佑
 */
public class PageBean<T> {
    private int pageNum;
    private int totalCount;
    private int pageSize;
    private List<T> data;
    private int totalPage;
    private int startPage;
    private int endPage;

    public int getStartPage() {
       return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public PageBean() {
        super();
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", data=" + data +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        int aa = totalCount/pageSize;
        return totalCount%pageSize==0?aa:aa+1;
    }

    public void setTotalPage(int pageCount) {
        this.totalPage = pageCount;
    }

    public PageBean(int pageNum, int totalCount, int pageSize, List<T> data) {

        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.data = data;
        this.totalPage = getTotalPage();
        this.startPage=pageNum-4;
        this.endPage = pageNum+5;
        if (pageNum<6){
            this.startPage=1;
            this.endPage=10;
        }
        if (pageNum>totalPage-5){
            this.startPage = totalPage-9;
            this.endPage=totalPage;
        }
        if (totalPage<10){
            this.startPage = 1;
            this.endPage=totalPage;
        }

    }
}

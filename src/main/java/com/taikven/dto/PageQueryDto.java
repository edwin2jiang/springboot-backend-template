package com.taikven.dto;

/**
 * 
 * @Date: 2023/4/11
 * @Version: 1.0
 */
public class PageQueryDto {
    private int pageNum = 1;
    private int pageSize = 20;

    public PageQueryDto() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}

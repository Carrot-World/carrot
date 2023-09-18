package com.carrot.domain;

public class SearchVO {
    private int category_id;
    private String loc1;
    private String loc2;
    private String loc3;
    private String title;
    private int pageNo;
    private String category_name;
    private int pageStartCnt;
    private int pageSize;

    public SearchVO() {
    }

    public SearchVO(String loc1, String loc2, String loc3) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.loc3 = loc3;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public String getLoc3() {
        return loc3;
    }

    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }

    public int getPageStartCnt() {
        return pageStartCnt;
    }

    public void setPageStartCnt(int pageStartCnt) {
        this.pageStartCnt = pageStartCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SearchVO{" +
                "category_id=" + category_id +
                ", loc1='" + loc1 + '\'' +
                ", loc2='" + loc2 + '\'' +
                ", loc3='" + loc3 + '\'' +
                ", title='" + title + '\'' +
                ", pageNo=" + pageNo +
                ", category_name='" + category_name + '\'' +
                ", pageStartCnt=" + pageStartCnt +
                ", pageSize=" + pageSize +
                '}';
    }
}

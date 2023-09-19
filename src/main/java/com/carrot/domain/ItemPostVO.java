package com.carrot.domain;

import java.util.Date;
import java.util.List;

public class ItemPostVO {
    private int id;
    private String title;
    private String content;
    private String loc1;
    private String loc2;
    private String loc3;
    private String writer;
    private int chat_cnt;
    private int price;
    private int status;
    private int category_id;
    private Date created_at;
    private List<ImageVO> imageList;
    private String category_name;
    private int hart_cnt;
    private String writer_nickname;

    public ItemPostVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getChat_cnt() {
        return chat_cnt;
    }

    public void setChat_cnt(int chat_cnt) {
        this.chat_cnt = chat_cnt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<ImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageVO> imageList) {
        this.imageList = imageList;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getHart_cnt() {
        return hart_cnt;
    }

    public void setHart_cnt(int hart_cnt) {
        this.hart_cnt = hart_cnt;
    }

    public String getWriter_nickname() {
        return writer_nickname;
    }

    public void setWriter_nickname(String writer_nickname) {
        this.writer_nickname = writer_nickname;
    }

    @Override
    public String toString() {
        return "ItemPostVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", loc1='" + loc1 + '\'' +
                ", loc2='" + loc2 + '\'' +
                ", loc3='" + loc3 + '\'' +
                ", writer='" + writer + '\'' +
                ", chat_cnt=" + chat_cnt +
                ", price=" + price +
                ", status=" + status +
                ", category_id=" + category_id +
                ", created_at=" + created_at +
                ", imageList=" + imageList +
                ", category_name='" + category_name + '\'' +
                ", hart_cnt=" + hart_cnt +
                ", writerNickName='" + writer_nickname + '\'' +
                '}';
    }
}

package com.carrot.domain;

import java.util.Date;

public class TownPostVO {
	
	
	private int id, read_cnt, reply_cnt, category_id;
	private String title, content, writer, loc1, loc2, loc3;
	private Date created_at;
	
	
	
	//getter setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	} 
	

}	


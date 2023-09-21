package com.carrot.domain;

import java.util.Date;
import java.util.List;

public class TownPostVO {
	
	
	private int id, read_cnt, reply_cnt, category_id;
	private String title, content, writer, loc1, loc2, loc3;
	private Date created_at;
	private String categoryName, nickname;
	private List<ReplyVO> replylist; //댓글 리스트
	private List<ReplyVO> rereplylist; //대댓글 리스트
	private String time; //시간저장
	
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
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<ReplyVO> getReplylist() {
		return replylist;
	}
	public void setReplylist(List<ReplyVO> replylist) {
		this.replylist = replylist;
	}
	
	public List<ReplyVO> getRereplylist() {
		return rereplylist;
	}
	public void setRereplylist(List<ReplyVO> rereplylist) {
		this.rereplylist = rereplylist;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "TownPostVO [id=" + id + ", read_cnt=" + read_cnt + ", reply_cnt=" + reply_cnt + ", category_id="
				+ category_id + ", title=" + title + ", content=" + content + ", writer=" + writer + ", loc1=" + loc1
				+ ", loc2=" + loc2 + ", loc3=" + loc3 + ", created_at=" + created_at + ", categoryName=" + categoryName
				+ ", nickname=" + nickname + ", replylist=" + replylist + ", rereplylist=" + rereplylist + ", time="
				+ time + "]";
	}
	
}	


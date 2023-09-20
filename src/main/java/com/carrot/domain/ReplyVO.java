package com.carrot.domain;

import java.util.Date;

public class ReplyVO {
	
	private int id, townPostId, status;
	private String parent, content, writer, nickname;
	private Date created_at;
	private String time;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTownPostId() {
		return townPostId;
	}
	public void setTownPostId(int townPostId) {
		this.townPostId = townPostId;
	}

	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", townPostId=" + townPostId + ", status=" + status + ", parent=" + parent
				+ ", content=" + content + ", writer=" + writer + ", nickname=" + nickname + ", created_at="
				+ created_at + ", time=" + time + "]";
	}
}

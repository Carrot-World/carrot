package com.carrot.domain;

import java.util.List;

public class UserVO {
	private String id, nickname, password, email, loc1, loc2, loc3;
	private List<AuthVO> authList;
	private int completed_cnt;
	
	
	public UserVO(String id, String nickname, String password, String email, String loc1, String loc2, String loc3,
			List<AuthVO> authList, int completed_cnt) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.loc3 = loc3;
		this.authList = authList;
		this.completed_cnt = completed_cnt;
	}
	
	public UserVO(String id, String nickname, String password, String email, String loc1, String loc2, String loc3) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.loc3 = loc3;
	}

	public UserVO() {
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	public List<AuthVO> getAuthList() {
		return authList;
	}


	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	
	public int getCompleted_cnt() {
		return completed_cnt;
	}

	public void setCompleted_cnt(int completed_cnt) {
		this.completed_cnt = completed_cnt;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", nickname=" + nickname + ", password=" + password + ", email=" + email + ", loc1="
				+ loc1 + ", loc2=" + loc2 + ", loc3=" + loc3 + ", authList=" + authList + ", completed_cnt="
				+ completed_cnt + "]";
	}
	
}

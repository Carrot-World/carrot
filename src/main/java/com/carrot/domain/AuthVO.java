package com.carrot.domain;

public class AuthVO {
	private String user_id, auth;

	public AuthVO(String user_id, String auth) {
		this.user_id = user_id;
		this.auth = auth;
	}
	
	public AuthVO(String user_id) {
		this.user_id = user_id;
	}


	public AuthVO() {
		
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "authVO [user_id=" + user_id + ", auth=" + auth + "]";
	}
	
}

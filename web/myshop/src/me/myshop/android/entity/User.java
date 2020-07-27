package me.myshop.android.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 5951781595784126658L;

	private Integer uid;
	private String password;
	private String nickname;

	private Boolean loginStatus;

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public String getPassword() {
		return password;
	}

	public Integer getUid() {
		return uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User{" + "uid=" + uid + ", nickname='" + nickname + '\'' + ", password='" + password + '\''
				+ ", loginStatus=" + loginStatus + '}';
	}
}

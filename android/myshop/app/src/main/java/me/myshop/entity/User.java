package me.myshop.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer uid;
    private String nickname;
    private String password;

    private Boolean loginStatus;

    public User(Integer uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public User(Integer uid, String nickname, String password, Boolean loginStatus) {
        this.uid = uid;
        this.nickname = nickname;
        this.password = password;
        this.loginStatus = loginStatus;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String username) {
        this.nickname = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", loginStatus=" + loginStatus +
                '}';
    }
}

package me.myshop.web.service;

import me.myshop.web.po.User;

public interface UserService {
	public User findUser(String username, String password);
}

package me.myshop.android.service;

import java.util.Map;

import me.myshop.android.entity.User;

public interface UserService {

	public Map<String, String> changePassword(User user, String newPassword);

	public Map<String, String> login(User user);

	public Map<String, String> logout(User user);

	public Map<String, String> register(User user);
}

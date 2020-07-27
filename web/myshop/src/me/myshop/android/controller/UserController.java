package me.myshop.android.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.myshop.android.entity.User;
import me.myshop.android.service.UserService;

@Controller("user")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, String> login(@RequestBody User user) {
		return userService.login(user);
	}

	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, String> logout(@RequestBody User user) {
		return userService.logout(user);
	}

	@RequestMapping("/register")
	@ResponseBody
	public Map<String, String> register(@RequestBody User user) {
		return userService.register(user);
	}

	@RequestMapping("/changepassword")
	@ResponseBody
	public Map<String, String> changePassword(@RequestBody User user, @RequestParam String newpassword) {
		return userService.changePassword(user, newpassword);
	}

}

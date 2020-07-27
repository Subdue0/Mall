package me.myshop.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.myshop.web.po.User;
import me.myshop.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String username, String password, Model model, HttpSession session) {
		User user = userService.findUser(username, password);
		if (user != null) {
			session.setAttribute("USER_SESSION", user);
			return "redirect:/good/list.action";
		}
		model.addAttribute("msg", "密码错误请重新输入");
		return "login";
	}

	//前台
	@RequestMapping(value = "/test.action")
	public String test() {
		return "shop";
	}
	
	//logout
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login.action";
	}
	@RequestMapping(value="/login.action",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	//注册
	@RequestMapping(value="/register.action")
	public String register() {
		return "register";
	}
}

package me.myshop.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.myshop.web.dao.UserDao;
import me.myshop.web.po.User;
import me.myshop.web.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User findUser(String username, String password) {
		User user = this.userDao.findUser(username, password);
		return user;
	}
}

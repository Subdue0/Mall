package me.myshop.web.dao;

import org.apache.ibatis.annotations.Param;

import me.myshop.web.po.User;

public interface UserDao {
	public User findUser(@Param("username") String username, @Param("password") String password);
}

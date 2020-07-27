package me.myshop.android.mapper;

import org.apache.ibatis.annotations.Param;

import me.myshop.android.entity.User;

public interface UserMapper {

	public void insertUser(User user);

	public User selectUserById(Integer id);

	public Integer selectUserNumber();

	public Integer selectLoginSatusById(Integer id);

	public void updateLoginStatusById(@Param("uid") Integer uid, @Param("status") Integer status);

	public void updatePasswordById(@Param("uid") Integer uid, @Param("newPassword") String newPassword);
}

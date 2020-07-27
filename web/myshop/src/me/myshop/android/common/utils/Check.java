package me.myshop.android.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import me.myshop.android.common.constant.Mall;
import me.myshop.android.entity.User;
import me.myshop.android.mapper.UserMapper;

public class Check {
	public static Map<String, String> login(User user, UserMapper userMapper) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		Integer uid = user.getUid();
		User queryUser = userMapper.selectUserById(uid);

		if (queryUser == null) {
			statusMap.put(Mall.STATUS, Mall.FAIL);
			statusMap.put(Mall.MSG, "用户不存在");
		} else {
			if (queryUser.getUid().equals(uid) && queryUser.getPassword().equals(user.getPassword())) {
				statusMap.put(Mall.STATUS, Mall.SUCCESS);
			} else {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "账号或者密码错误");
			}
		}

		return statusMap;
	}
}

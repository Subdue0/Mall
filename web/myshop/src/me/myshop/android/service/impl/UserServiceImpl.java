package me.myshop.android.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.myshop.android.common.constant.Mall;
import me.myshop.android.common.utils.Check;
import me.myshop.android.entity.User;
import me.myshop.android.mapper.UserMapper;
import me.myshop.android.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public Map<String, String> changePassword(User user, String newPassword) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		int uid = user.getUid();
		if (userMapper.selectLoginSatusById(uid) == 1) {
			User queryUser = userMapper.selectUserById(uid);

			if (queryUser.getPassword().equals(user.getPassword())) {
				statusMap.put(Mall.STATUS, Mall.SUCCESS);
				userMapper.updatePasswordById(uid, newPassword);
			} else {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "原密码错误");
			}
		} else {
			statusMap.put(Mall.STATUS, Mall.FAIL);
			statusMap.put(Mall.MSG, "请先登录后再修改密码");
		}

		return statusMap;
	}

	@Override
	public Map<String, String> login(User user) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		Integer uid = user.getUid();
		User queryUser = userMapper.selectUserById(uid);

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid) == 1) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户已登录");
			} else {
				statusMap.put("nickname", queryUser.getNickname());
				userMapper.updateLoginStatusById(uid, 1);
			}

		}

		return statusMap;
	}

	@Override
	public Map<String, String> logout(User user) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		Integer uid = user.getUid();

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid) == 0) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户已退出");
			} else {
				statusMap.put(Mall.STATUS, Mall.SUCCESS);
				userMapper.updateLoginStatusById(uid, 0);
			}
		}

		return statusMap;
	}

	@Override
	public Map<String, String> register(User user) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		String nickname = user.getNickname();
		String password = user.getPassword();

		if (nickname == null || password == null || nickname.isEmpty() || password.isEmpty()) {
			statusMap.put(Mall.STATUS, Mall.FAIL);
			statusMap.put(Mall.MSG, "用户名或者密码为空");
			return statusMap;
		}

		/**
		 * 在处理注册前，必须生产出一个合格的uid
		 */
		int uid = 0;
		int user_number = userMapper.selectUserNumber();
		int start_id = 1;
		int end_id = Mall.MAX_USER_NUMBER;
		if (user_number <= end_id) {
			if (user_number <= end_id / 2) {
				// 返回指定范围的随机数(m-n之间)的公式：Math.random()*(n+1-m)+m
				// 返回1-9999之间的随机数id
				uid = (int) (Math.random() * end_id + start_id);

				// 保证订单id是唯一的
				while (userMapper.selectUserById(uid) != null) {
					uid = (int) (Math.random() * end_id + start_id);
				}

			} else {
				for (int i = 1; i <= end_id; i++) {
					if (userMapper.selectUserById(i) == null) {
						uid = i;
						break;
					}
				}
			}
			/**
			 * 开始处理用户注册
			 */
			user.setUid(uid);
			userMapper.insertUser(user);
			userMapper.updateLoginStatusById(uid, 0);
			statusMap.put(Mall.STATUS, Mall.SUCCESS);
			statusMap.put(Mall.ID, String.valueOf(uid));
		} else {
			statusMap.put(Mall.STATUS, Mall.FAIL);
			statusMap.put(Mall.MSG, "用户量已达最大上限");
		}

		return statusMap;
	}

}

package me.myshop.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.myshop.web.dao.AndroidDao;
import me.myshop.web.po.Order;
import me.myshop.web.po.User;
import me.myshop.web.service.AndroidService;

@Service
public class AndroidServiceImpl implements AndroidService {
	@Autowired
	private AndroidDao androidDao;

	@Override
	public String changePassword(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitData(int id, String data) {
		androidDao.saveShoppingCarData(id, data);
	}

	@Override
	public String submitOrderData(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}

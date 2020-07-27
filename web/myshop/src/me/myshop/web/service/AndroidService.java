package me.myshop.web.service;

import me.myshop.web.po.Order;
import me.myshop.web.po.User;

public interface AndroidService {
	public String changePassword(User user);

	public String login(User user);

	public String register(User user);

	public void submitData(int id, String data);

	public String submitOrderData(Order order);
}

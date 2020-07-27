package me.myshop.android.service;

import java.util.Map;

import me.myshop.android.entity.User;
import me.myshop.android.entity.UserAndOrder;

public interface OrderService {

	public Map<String, String> deleteOrderById(Integer id, User user);

	public Map<String, String> submitOrder(UserAndOrder userAndOrder);

	public Map<String, String> modifyOrder(UserAndOrder userAndOrder);

	public Object showMyOrders(User user);
}

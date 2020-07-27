package me.myshop.web.service;

import java.util.List;

import me.myshop.web.common.utils.Page;
import me.myshop.web.po.Og;
import me.myshop.web.po.Order;

public interface OrderService {
	public Page<Order> findOrderList(Integer page, Integer rows, String custName);

	public List<Og> getOrderGood(Integer id);

	public Order getOrderById(Integer id);

	public int updateOrder(Order order);

	public int setOrder(Integer id);

	public int setState(Integer id);

	public int deleteOrder(Integer id);

}

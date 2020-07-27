package me.myshop.web.dao;

import java.util.List;

import me.myshop.web.po.Og;
import me.myshop.web.po.Order;

public interface OrderDao {
	public List<Order> selectOrderList(Order order);

	public Integer selectOrderListCount(Order order);

	public List<Og> selectOrderGood(Integer id);

	public int setOrder(Integer id);

	public int setState(Integer id);

	public Order getOrderById(Integer id);

	public int updateOrder(Order order);

	public int deleteOrder(Integer id);
}
package me.myshop.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.myshop.web.common.utils.Page;
import me.myshop.web.dao.OrderDao;
import me.myshop.web.po.Og;
import me.myshop.web.po.Order;
import me.myshop.web.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	// 查询订单列表
	@Override
	public Page<Order> findOrderList(Integer page, Integer rows, String custName) {
		Order order = new Order();
		// 判断
		if (StringUtils.isNotBlank(custName)) {
			order.setCust_name(custName);
		}

		List<Order> orders = orderDao.selectOrderList(order);
		Integer count = orderDao.selectOrderListCount(order);

		Page<Order> result = new Page<Order>();

		result.setPage(page);
		result.setRows(orders);
		result.setSize(rows);
		result.setTotal(count);

		return result;
	}

	// 获取配件清单
	@Override
	public List<Og> getOrderGood(Integer id) {
		List<Og> ogs = orderDao.selectOrderGood(id);
		return ogs;
	}

	// 设置订单
	@Override
	public int setOrder(Integer id) {
		return orderDao.setOrder(id);
	}

	@Override
	public int setState(Integer id) {
		return orderDao.setState(id);
	}

	// 获取订单并更新
	@Override
	public Order getOrderById(Integer id) {
		Order order = orderDao.getOrderById(id);
		return order;
	}

	@Override
	public int updateOrder(Order order) {
		return orderDao.updateOrder(order);
	}
	//删除订单
	@Override
	public int deleteOrder(Integer id) {
		return orderDao.deleteOrder(id);
	}

}

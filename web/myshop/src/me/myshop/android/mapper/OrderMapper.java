package me.myshop.android.mapper;

import java.util.Date;
import java.util.List;

import me.myshop.android.entity.Goods;
import me.myshop.android.entity.Order;

public interface OrderMapper {

	public Date selectOrderTimeById(Integer id);

	public List<Order> selectMyOrders(Integer uid);

	public Integer selectOrderIdById(Integer id);

	public Integer selectUserIdById(Integer id);

	public List<Goods> selectBuyGoodsById(Integer id);

	public Integer selectOrderNumber();

	public void deleteOrderById(Integer id);

	public void insertOrder(Order order);

	public void updateOrder(Order order);
}

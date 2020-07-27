package me.myshop.android.service.impl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.myshop.android.common.constant.Mall;
import me.myshop.android.common.utils.Check;
import me.myshop.android.entity.Goods;
import me.myshop.android.entity.Order;
import me.myshop.android.entity.User;
import me.myshop.android.entity.UserAndOrder;
import me.myshop.android.mapper.GoodsMapper;
import me.myshop.android.mapper.OrderMapper;
import me.myshop.android.mapper.UserMapper;
import me.myshop.android.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public Object showMyOrders(User user) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		Integer uid = user.getUid();

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid).equals(0)) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户未登录");
			} else {
				/**
				 * 开始查询用户订单
				 */
				Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				List<Order> order_list = orderMapper.selectMyOrders(uid);
				for (Order order : order_list) {
					order.setCreateTime(format.format(orderMapper.selectOrderTimeById(order.getId())));
					order.setGoodsList(orderMapper.selectBuyGoodsById(order.getId()));
				}
				System.out.println(order_list);
				return order_list;
			}

		}
		return statusMap;
	}

	@Override
	public Map<String, String> deleteOrderById(Integer id, User user) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		Integer uid = user.getUid();

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid).equals(0)) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户未登录");
			} else {
				if (orderMapper.selectOrderIdById(id) == null) {
					statusMap.put(Mall.STATUS, Mall.FAIL);
					statusMap.put(Mall.MSG, "订单不存在");
				} else {
					if (orderMapper.selectUserIdById(id).equals(uid)) {
						orderMapper.deleteOrderById(id);
						statusMap.put(Mall.STATUS, Mall.SUCCESS);
					} else {
						statusMap.put(Mall.STATUS, Mall.FAIL);
						statusMap.put(Mall.MSG, "该订单不属于此用户");
					}
				}
			}
		}

		return statusMap;
	}

	@Override
	public Map<String, String> submitOrder(UserAndOrder userAndOrder) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		User user = userAndOrder.getUser();
		Order order = userAndOrder.getOrder();
		List<Goods> goods_list = order.getGoodsList();

		Integer uid = user.getUid();

		/**
		 * 订单数据安全控制
		 */

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid).equals(0)) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户未登录");
				return statusMap;
			} else {
				if (userMapper.selectUserById(order.getUid()) == null) {
					statusMap.put(Mall.STATUS, Mall.FAIL);
					statusMap.put(Mall.MSG, "订单中的uid不存在");
					return statusMap;
				} else {
					if (!order.getUid().equals(user.getUid())) {
						statusMap.put(Mall.STATUS, Mall.FAIL);
						statusMap.put(Mall.MSG, "订单中的uid与用户的uid不一致");
						return statusMap;
					}
				}

				for (Goods goods : goods_list) {
					if (goodsMapper.selectGoodsIdById(goods.getId()) == null) {
						statusMap.put(Mall.STATUS, Mall.FAIL);
						statusMap.put(Mall.MSG, "订单中有商品已被下架或不存在");
						return statusMap;
					}
				}
				statusMap.put(Mall.STATUS, Mall.SUCCESS);
			}
		} else {
			return statusMap;
		}

		int start_id = Mall.MIN_ORDER_NUMBER;
		int end_id = Mall.MAX_ORDER_NUMBER;
		int order_number = orderMapper.selectOrderNumber();
		if (order_number >= end_id) {
			statusMap.put(Mall.STATUS, Mall.FAIL);
			statusMap.put(Mall.MSG, "订单量已达最大上限");

			return statusMap;
		}

		/**
		 * 订单预处理，为插入订单做准备，先生成一个合格的order_id
		 */
		int order_id = 0;
		if (order_number <= end_id / 2) {

			// 返回指定范围的随机数(m-n之间)的公式：Math.random()*(n+1-m)+m 返回1-9999之间的随机数id
			order_id = (int) (Math.random() * end_id + start_id);

			// 保证order_id是唯一的
			while (orderMapper.selectOrderIdById(order_id) != null) {
				order_id = (int) (Math.random() * end_id + start_id);
			}
		} else {
			for (int i = 1; i <= end_id; i++) {
				// 保证order_id是唯一的
				if (orderMapper.selectOrderIdById(i) == null) {
					order_id = i;
					break;
				}
			}
		}

		/**
		 * 有了之前安全控制和order_id之后，就开始往数据库插入订单
		 */
		order.setId(order_id);
		orderMapper.insertOrder(order);
		statusMap.put(Mall.ID, String.valueOf(order_id));

		/**
		 * 处理订单创建时间
		 */
		Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time = orderMapper.selectOrderTimeById(order_id);

		statusMap.put("orderCreateTime", format.format(time));

		/**
		 * 插入订单中的所有商品
		 */
		for (Goods goods : goods_list) {
			goodsMapper.insertOrderGoods(order_id, goods);
		}

		return statusMap;
	}

	@Override
	public Map<String, String> modifyOrder(UserAndOrder userAndOrder) {
		// 存储返回给客户端的状态
		Map<String, String> statusMap = new LinkedHashMap<>();

		User user = userAndOrder.getUser();
		Order order = userAndOrder.getOrder();

		Integer uid = user.getUid();

		// 使用工具类检查账号密码
		statusMap = Check.login(user, userMapper);

		if (statusMap.get(Mall.STATUS).equals(Mall.SUCCESS)) {
			if (userMapper.selectLoginSatusById(uid).equals(0)) {
				statusMap.put(Mall.STATUS, Mall.FAIL);
				statusMap.put(Mall.MSG, "用户未登录");
			} else {
				if (orderMapper.selectOrderIdById(order.getId()) == null) {
					statusMap.put(Mall.STATUS, Mall.FAIL);
					statusMap.put(Mall.MSG, "订单不存在");
				} else {
					if (orderMapper.selectUserIdById(order.getId()).equals(order.getUid())) {
						orderMapper.updateOrder(order);
						statusMap.put(Mall.STATUS, Mall.SUCCESS);
					} else {
						statusMap.put(Mall.STATUS, Mall.FAIL);
						statusMap.put(Mall.MSG, "该订单不属于此用户");
					}
				}
			}

		}

		return statusMap;
	}

}

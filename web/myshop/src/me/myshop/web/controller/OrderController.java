package me.myshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.myshop.web.common.utils.Page;
import me.myshop.web.po.Order;
import me.myshop.web.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	//跳转
	@RequestMapping(value = "/order/toOrders.action")
	public String toOrders() {
		return "redirect:/order/list.action";
	}

	//查询订单列表
	@RequestMapping(value = "/order/list.action")
	public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "1") Integer rows,
			String custName, Model model) {
		Page<Order> orders = orderService.findOrderList(page, rows, custName);
		model.addAttribute("page", orders);
		return "orders";
	}

	//获取订单并更新
	@RequestMapping("/order/getOrderById.action")
	@ResponseBody
	public Order getOrderById(Integer id) {
		Order order = orderService.getOrderById(id);
		return order;
	}
	@RequestMapping("/order/update.action")
	@ResponseBody
	public String orderUpdate(Order order) {
		int rows = orderService.updateOrder(order);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
	//删除订单
	@RequestMapping("/order/delete.action")
	@ResponseBody
	public String orderDelete(Integer id) {
		int rows = orderService.deleteOrder(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
	//设置订单
	@RequestMapping(value = "/order/setorder.action")
	public String setOrder(Integer id) {
		int rows = orderService.setOrder(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
	@RequestMapping(value = "/order/setstate.action")
	public String setState(Integer id) {
		int rows = orderService.setState(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	//json
	@RequestMapping(value = "/order/json.action")
	@ResponseBody
	public Page<Order> Androidlist(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "1") Integer rows, String orderId, Model model) {
		Page<Order> orders = orderService.findOrderList(page, rows, orderId);
		model.addAttribute("page", orders);
		return orders;
	}

}

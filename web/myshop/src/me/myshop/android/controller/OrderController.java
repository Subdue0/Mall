package me.myshop.android.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import me.myshop.android.entity.User;
import me.myshop.android.entity.UserAndOrder;
import me.myshop.android.service.OrderService;

@Controller("order")
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/submit")
	@ResponseBody
	public Map<String, String> submit(@RequestBody UserAndOrder userAndOrder) {
		return orderService.submitOrder(userAndOrder);
	}

	@RequestMapping("/modify")
	@ResponseBody
	public Map<String, String> modify(@RequestBody UserAndOrder userAndOrder) {
		return orderService.modifyOrder(userAndOrder);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(@RequestParam int id, @RequestBody User user) {
		return orderService.deleteOrderById(id, user);
	}

	@RequestMapping("/showmyorders")
	@ResponseBody
	public Object showMyOrders(@RequestBody User user) {
		return orderService.showMyOrders(user);
	}
}

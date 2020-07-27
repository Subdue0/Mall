package me.myshop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import me.myshop.web.po.Og;
import me.myshop.web.service.OrderService;

@Controller
public class OgController {
	@Autowired
	private OrderService orderService;

	//查询配件清单
	@RequestMapping(value = "/order/good.action")
	public String list(Integer id, Model model) {
		List<Og> ogs = orderService.getOrderGood(id);
		model.addAttribute("ogs", ogs);
		return "ogs";
	}
}

package me.myshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import me.myshop.web.common.utils.Page;
import me.myshop.web.po.Good;
import me.myshop.web.service.GoodService;

@Controller
public class ShopController {
	@Autowired
	private GoodService goodService;

	//前台控制器
	@RequestMapping(value = "/shop/list.action")
	public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "1") Integer rows,
			String goodName, Model model) {
		Page<Good> goods = goodService.findGoodList(page, rows, goodName);
		model.addAttribute("page", goods);
		return "shop";
	}
	//
}

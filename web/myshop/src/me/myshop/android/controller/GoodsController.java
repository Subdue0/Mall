package me.myshop.android.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.myshop.android.entity.Goods;
import me.myshop.android.service.GoodsService;

@Controller("goods")
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/showallgoods")
	@ResponseBody
	public List<Goods> show() {
		return goodsService.selectAllGoods();
	}

}

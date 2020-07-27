package me.myshop.android.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.myshop.android.entity.Goods;
import me.myshop.android.mapper.GoodsMapper;
import me.myshop.android.service.GoodsService;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<Goods> selectAllGoods() {
		return goodsMapper.selectAllGoods();
	}

}

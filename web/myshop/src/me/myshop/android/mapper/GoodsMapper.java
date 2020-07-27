package me.myshop.android.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.myshop.android.entity.Goods;

public interface GoodsMapper {
	public void insertOrderGoods(@Param("orderId") Integer orderId, @Param("goods") Goods goods);

	public Integer selectGoodsIdById(Integer id);

	public List<Goods> selectAllGoods();
}

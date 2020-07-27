package me.myshop.web.dao;

import java.util.List;

import me.myshop.web.po.Good;

public interface GoodDao {
	// 查询配件
	public List<Good> selectGoodList(Good good);

	public Integer selectGoodListCount(Good good);

	// 上架配件
	public int createGood(Good good);

	// 获取配件并更新
	public Good getGoodById(Integer id);

	public int updateGood(Good good);

	// 删除配件
	public int deleteGood(Integer id);

	// 设置配件
	public int setGood(Integer id);

	// JSON
	public List<Good> selectJSONList(Good good);
}
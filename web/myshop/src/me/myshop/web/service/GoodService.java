package me.myshop.web.service;

import java.util.List;

import me.myshop.web.common.utils.Page;
import me.myshop.web.po.Good;

public interface GoodService {
	public Page<Good> findGoodList(Integer page, Integer rows, String goodName);

	public int createGood(Good good);

	public Good getGoodById(Integer id);

	public int updateGood(Good good);

	public int deleteGood(Integer id);

	public int setGood(Integer id);

	// JSON
	public List<Good> findJsonList();

}

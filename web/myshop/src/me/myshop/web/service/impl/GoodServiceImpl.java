package me.myshop.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.myshop.web.common.utils.Page;
import me.myshop.web.dao.GoodDao;
import me.myshop.web.po.Good;
import me.myshop.web.service.GoodService;

@Service("goodService")
@Transactional
public class GoodServiceImpl implements GoodService {
	@Autowired
	private GoodDao goodDao;

	@Override
	public Page<Good> findGoodList(Integer page, Integer rows, String goodName) {
		Good good = new Good();
		// 判断是否为空
		if (StringUtils.isNotBlank(goodName)) {
			good.setGood_name(goodName);
		}

		List<Good> goods = goodDao.selectGoodList(good);
		Integer count = goodDao.selectGoodListCount(good);

		Page<Good> result = new Page<Good>();

		result.setPage(page);
		result.setRows(goods);
		result.setSize(rows);
		result.setTotal(count);

		return result;
	}

	@Override
	public int createGood(Good good) {
		return goodDao.createGood(good);
	}

	// 获取配件并更新
	@Override
	public Good getGoodById(Integer id) {
		Good good = goodDao.getGoodById(id);
		return good;
	}

	@Override
	public int updateGood(Good good) {
		return goodDao.updateGood(good);
	}

	// 删除配件
	@Override
	public int deleteGood(Integer id) {
		return goodDao.deleteGood(id);
	}

	// 设置配件
	@Override
	public int setGood(Integer id) {
		return goodDao.setGood(id);
	}

	// JSON
	@Override
	public List<Good> findJsonList() {
		Good good = new Good();
		List<Good> goods = goodDao.selectGoodList(good);
		return goods;
	}
}

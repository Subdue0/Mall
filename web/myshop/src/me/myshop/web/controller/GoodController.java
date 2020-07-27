package me.myshop.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import me.myshop.web.common.utils.Page;
import me.myshop.web.po.Good;
import me.myshop.web.service.GoodService;

@Controller
public class GoodController {
	@Autowired
	private GoodService goodService;

	//查询配件列表
	@RequestMapping(value = "/good/list.action")
	public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "1") Integer rows,
			String goodName, Model model) {
		Page<Good> goods = goodService.findGoodList(page, rows, goodName);
		model.addAttribute("page", goods);
		return "goods";
	}

	//创建配件
	@RequestMapping(value = "/good/create.action")
	@ResponseBody
	public String goodCreate(Good good, HttpSession session) {
		int rows = goodService.createGood(good);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	//获取配件并更新
	@RequestMapping("/good/getGoodById.action")
	@ResponseBody
	public Good getGoodById(Integer id,Model model) {
		Good good = goodService.getGoodById(id);
		String picture=good.getPic_url();
		model.addAttribute(picture);
		
		return good;
	}
	@RequestMapping("/good/update.action")
	@ResponseBody
	public String goodUpdate(Good good) {
		int rows = goodService.updateGood(good);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	//删除配件
	@RequestMapping("/good/delete.action")
	@ResponseBody
	public String goodDelete(Integer id) {
		int rows = goodService.deleteGood(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
	//售罄
	@RequestMapping("/good/setgood.action")
	public String setGood(Integer id) {
		goodService.setGood(id);
		return null;
	}

	/*
	 *
	 * 文件上传
	 * 
	 */
	@RequestMapping("/fileUpload.action")
	@ResponseBody
	public String handlerFormUpload(List<MultipartFile> uploadfile, HttpServletRequest request) {
		if (!uploadfile.isEmpty() && uploadfile.size() > 0) {
			for (MultipartFile file : uploadfile) {
				String originalFilename = file.getOriginalFilename();
				//拼接路径
				String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				int num = t.indexOf(".metadata");
				String dirPath = t.substring(1, num).replace('/', '\\') + request.getContextPath().replace("/", "")
						+ "\\WebContent\\images\\";
				try {
					file.transferTo(new File(dirPath + originalFilename));
					System.out.println(dirPath + originalFilename);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	//json
	@RequestMapping(value = "/good/json.action")
	@ResponseBody
	public Page<Good> Androidlist(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "1") Integer rows, String goodName, Model model) {
		Page<Good> goods = goodService.findGoodList(page, rows, goodName);
		model.addAttribute("page", goods);
		return goods;
	}

	//json
	@RequestMapping(value = "/good/showJsonList.action")
	@ResponseBody
	public List<Good> goodJsonList() {
		List<Good> goods = goodService.findJsonList();
		return goods;
	}
}

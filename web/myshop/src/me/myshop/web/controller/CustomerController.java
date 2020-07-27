package me.myshop.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.myshop.web.po.Customer;
import me.myshop.web.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	//查询客户列表
	@RequestMapping("/customer/list.action")
	public String list(Model model, String custName) {
		List<Customer> customers = customerService.findCustomerList(custName);
		model.addAttribute("customers", customers);
		return "customers";
	}

	//创建客户
	@RequestMapping(value = "/customer/create.action")
	@ResponseBody
	public String customerCreate(Customer customer, HttpSession session) {
		int rows = customerService.createCustomer(customer);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	//获取客户并更新
	@RequestMapping("/customer/getCustomerById.action")
	@ResponseBody
	public Customer getCustomerById(Integer id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}
	@RequestMapping("/customer/update.action")
	@ResponseBody
	public String customerUpdate(Customer customer) {
		int rows = customerService.updateCustomer(customer);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	//删除客户
	@RequestMapping("/customer/delete.action")
	@ResponseBody
	public String customerDelete(Integer id) {
		int rows = customerService.deleteCustomer(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}
}

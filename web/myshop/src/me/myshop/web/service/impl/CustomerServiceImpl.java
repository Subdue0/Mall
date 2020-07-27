package me.myshop.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.myshop.web.dao.CustomerDao;
import me.myshop.web.po.Customer;
import me.myshop.web.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	// 查询客户列表
	@Override
	public List<Customer> findCustomerList(String custName) {
		Customer customer = new Customer();

		if (StringUtils.isNotBlank(custName)) {
			customer.setCust_name(custName);
		}
		List<Customer> customers = customerDao.selectCustomerList(customer);
		return customers;
	}

	// 创建客户
	@Override
	public int createCustomer(Customer customer) {
		return customerDao.createCustomer(customer);
	}

	// 获取客户id
	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerDao.getCustomerById(id);
		return customer;
	}

	// 更新客户
	@Override
	public int updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	// 删除客户
	@Override
	public int deleteCustomer(Integer id) {
		return customerDao.deleteCustomer(id);
	}

}

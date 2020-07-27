package me.myshop.web.dao;

import java.util.List;

import me.myshop.web.po.Customer;

public interface CustomerDao {
	// 查询客户
	public List<Customer> selectCustomerList(Customer customer);

	// 创建客户
	public int createCustomer(Customer customer);

	// 获取客户
	public Customer getCustomerById(Integer id);

	// 更新客户
	public int updateCustomer(Customer customer);

	// 删除客户
	public int deleteCustomer(Integer id);
}

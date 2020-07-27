package me.myshop.web.service;

import java.util.List;

import me.myshop.web.po.Customer;

public interface CustomerService {
	public List<Customer> findCustomerList(String custName);

	public int createCustomer(Customer customer);

	public Customer getCustomerById(Integer id);

	public int updateCustomer(Customer customer);

	public int deleteCustomer(Integer id);
}

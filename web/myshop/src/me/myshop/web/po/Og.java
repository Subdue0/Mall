package me.myshop.web.po;

import java.io.Serializable;

public class Og implements Serializable {
	private static final long serialVersionUID = 1l;
	private Integer good_id;
	private String good_name;
	private Integer good_num;
	private double total_price;

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public Integer getGood_id() {
		return good_id;
	}

	public void setGood_id(Integer good_id) {
		this.good_id = good_id;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}

	public Integer getGood_num() {
		return good_num;
	}

	public void setGood_num(Integer good_num) {
		this.good_num = good_num;
	}

}

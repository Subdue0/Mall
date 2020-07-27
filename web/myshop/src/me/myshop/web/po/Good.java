package me.myshop.web.po;

import java.io.Serializable;

public class Good implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer good_id;
	private String good_name;
	private double good_price;
	private String type_name;
	private String good_detail;
	private Integer type_id;
	private String pic_url;

	public String getPic_url() {
		return pic_url;
	}

	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
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

	public double getGood_price() {
		return good_price;
	}

	public void setGood_price(double good_price) {
		this.good_price = good_price;
	}

	public String getGood_detail() {
		return good_detail;
	}

	public void setGood_detail(String good_detail) {
		this.good_detail = good_detail;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

}

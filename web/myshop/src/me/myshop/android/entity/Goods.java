package me.myshop.android.entity;

import java.io.Serializable;

public class Goods implements Serializable {
	private static final long serialVersionUID = -1755389334088309243L;

	private Integer id;
	private String imageDetail;
	private String imageIcon;
	private Integer number = 1;

	private Double price;
	private String title;

	private String status;

	private Integer type;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getImageDetail() {
		return imageDetail;
	}

	public String getImageIcon() {
		return imageIcon;
	}

	public Integer getNumber() {
		return number;
	}

	public Double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public Double getTotalCost() {
		return price * number;
	}

	public Integer getType() {
		return type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImageDetail(String imageDetail) {
		this.imageDetail = imageDetail;
	}

	public void setImageIcon(String imageIcon) {
		this.imageIcon = imageIcon;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", imageDetail=" + imageDetail + ", imageIcon=" + imageIcon + ", number=" + number
				+ ", price=" + price + ", title=" + title + ", status=" + status + ", type=" + type + "]";
	}
}

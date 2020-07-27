package me.myshop.android.entity;

import java.io.Serializable;

public class RecInfo implements Serializable {
	private static final long serialVersionUID = -4552528932411683565L;

	private String address;
	private String name;
	private String phone;

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ReceiverAddress{" + "name='" + name + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\''
				+ '}';
	}
}

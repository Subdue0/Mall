package me.myshop.android.entity;

import java.io.Serializable;

public class UserAndOrder implements Serializable {
	private static final long serialVersionUID = -1279529014332061715L;

	private User user;
	private Order order;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "UserAndOrder{" + "user=" + user + ", order=" + order + '}';
	}
}

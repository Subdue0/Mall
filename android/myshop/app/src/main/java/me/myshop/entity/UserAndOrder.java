package me.myshop.entity;

public class UserAndOrder {
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
        return "UserAndOrder{" +
                "user=" + user +
                ", order=" + order +
                '}';
    }
}

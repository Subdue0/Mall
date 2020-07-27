package me.myshop.entity;

import java.io.Serializable;

public class Goods implements Serializable {
    private Integer id;
    private String title;
    private Double price;
    private Integer number = 1;

//    //匿名用户数据图片
//    private Integer imageIcon;
//    private Integer imageDetail;

    private String imageIcon;
    private String imageDetail;

    private Integer type;

    public Goods() {
    }

    public Goods(Integer id, String title, Double price, Integer number, String imageIcon, String imageDetail, Integer type) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.number = number;
        this.imageIcon = imageIcon;
        this.imageDetail = imageDetail;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getTotalCost() {
        return price * number;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", imageIcon=" + imageIcon +
                ", imageDetail=" + imageDetail +
                ", type=" + type +
                '}';
    }
}

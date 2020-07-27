package me.myshop.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ShoppingCar implements Serializable {
    private Double totalCost;

    private Map<Integer, Boolean> checkMap;
    private List<Goods> goodsList;

    public ShoppingCar(Double totalCost, Map<Integer, Boolean> checkMap, List<Goods> goodsList) {
        this.totalCost = totalCost;
        this.checkMap = checkMap;
        this.goodsList = goodsList;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Map<Integer, Boolean> getCheckMap() {
        return checkMap;
    }

    public void setCheckMap(Map<Integer, Boolean> checkMap) {
        this.checkMap = checkMap;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "totalCost=" + totalCost +
                ", checkMap=" + checkMap +
                ", goodsList=" + goodsList +
                '}';
    }
}

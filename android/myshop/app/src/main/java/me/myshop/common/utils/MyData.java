package me.myshop.common.utils;

import java.util.ArrayList;
import java.util.List;

import me.myshop.entity.Goods;
import me.myshop.entity.Order;
import me.myshop.entity.RecInfo;
import me.myshop.entity.User;

/**
 * 单例对象MyData，用于单个用户内的数据传输
 */
public class MyData {
    private User mUser;

    /**
     * 数据源
     */
    private List<Goods> mGoodsList = new ArrayList<>();
    private List<Order> mMyOrderList = new ArrayList<>();

    private volatile static MyData sMyData;

    private MyData() {
    }

    public static MyData getInstance() {


        if (sMyData == null) {


            synchronized (MyData.class) {


                if (sMyData == null) {

                    sMyData = new MyData();

                }

            }
        }

        return sMyData;
    }


    //返回当前用户实体类
    public User getUser() {
        return mUser;
    }

    //设置当前用户实体类中的属性
    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public List<Goods> getGoodsList() {
        return mGoodsList;
    }

    public void setGoodsList(List<Goods> mGoodsList) {
        this.mGoodsList = mGoodsList;
    }

    //获取订单列表
    public List<Order> getMyOrderList() {
        return mMyOrderList;
    }

    //设置订单列表
    public void setMyOrderList(List<Order> my_order) {
        this.mMyOrderList = my_order;
    }

    //添加订单列表
    public void addMyOrderList(Order my_order) {
        this.mMyOrderList.add(my_order);
    }

    //修改订单数据
    public void modifyOrder(int id, RecInfo rec_info, String pay_mode) {
        for (Order order : mMyOrderList) {
            if (order.getId() == id) {
                order.setRecInfo(rec_info);
                order.setPayMode(pay_mode);
                break;
            }
        }
    }

    //删除订单
    public void deleteOrder(int id) {
        for (Order order : mMyOrderList) {
            if (order.getId() == id) {
                mMyOrderList.remove(order);
                break;
            }
        }
    }

    //退出，清空用户数据
    public void clearData() {
        mUser = null;
        mGoodsList.clear();
        mMyOrderList.clear();
    }
}

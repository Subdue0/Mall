package me.myshop.entity;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private Integer uid;
    private Integer id;
    private String payMode;
    private Double totalCost;
    private String logisticsStatus;
    private String logisticsProgress;

    private RecInfo recInfo;
    private List<Goods> goodsList;

    private String createTime;


    public Order(Integer id, Integer uid, String payMode, RecInfo recInfo) {
        this.id = id;
        this.uid = uid;
        this.payMode = payMode;
        this.recInfo = recInfo;
    }


    public Order(Integer uid, String payMode, Double totalCost, String logisticsStatus, RecInfo recInfo, List<Goods> goodsList) {
        this.uid = uid;
        this.payMode = payMode;
        this.totalCost = totalCost;
        this.logisticsStatus = logisticsStatus;
        this.recInfo = recInfo;
        this.goodsList = goodsList;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getLogisticsProgress() {
        return logisticsProgress;
    }

    public void setLogisticsProgress(String logisticsProgress) {
        this.logisticsProgress = logisticsProgress;
    }

    public RecInfo getRecInfo() {
        return recInfo;
    }

    public void setRecInfo(RecInfo recInfo) {
        this.recInfo = recInfo;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uid=" + uid +
                ", id=" + id +
                ", payMode='" + payMode + '\'' +
                ", totalCost=" + totalCost +
                ", logisticsStatus='" + logisticsStatus + '\'' +
                ", logisticsProgress='" + logisticsProgress + '\'' +
                ", recInfo=" + recInfo +
                ", goodsList=" + goodsList +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}

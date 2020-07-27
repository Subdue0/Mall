package me.myshop.android.entity;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 2642803085212201606L;

	private Integer id;
	private Integer uid;

	private String payMode;

	private Double totalCost;

	private String CreateTime;

	private String logisticsProgress;
	private String logisticsStatus;

	private RecInfo recInfo;
	private List<Goods> goodsList;

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public Integer getId() {
		return id;
	}

	public String getLogisticsProgress() {
		return logisticsProgress;
	}

	public String getLogisticsStatus() {
		return logisticsStatus;
	}

	public String getPayMode() {
		return payMode;
	}

	public RecInfo getRecInfo() {
		return recInfo;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public Integer getUid() {
		return uid;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogisticsProgress(String logisticsProgress) {
		this.logisticsProgress = logisticsProgress;
	}

	public void setLogisticsStatus(String logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public void setRecInfo(RecInfo recInfo) {
		this.recInfo = recInfo;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String CreateTime) {
		this.CreateTime = CreateTime;
	}

	@Override
	public String toString() {
		return "Order [goodsList=" + goodsList + ", id=" + id + ", logisticsProgress=" + logisticsProgress
				+ ", logisticsStatus=" + logisticsStatus + ", payMode=" + payMode + ", recInfo=" + recInfo
				+ ", totalCost=" + totalCost + ", uid=" + uid + ", CreateTime=" + CreateTime + "]";
	}
}

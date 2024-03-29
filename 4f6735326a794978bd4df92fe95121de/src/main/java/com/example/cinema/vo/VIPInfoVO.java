package com.example.cinema.vo;



/**
 * Created by liying on 2019/4/15.
 */
public class VIPInfoVO {
	
	private int id;
	
	/**
	 * 会员卡简介
	 */
    private String description;
    
    /**
     * 会员卡名称
     */
    private String name;
    
    /**
     * 会员卡价格
     */
    private double price;
    
    /**
     * 最低优惠额度
     */
    private double minimumCharge;
    
    /**
     * 赠送优惠额度
     */
    private double extraCharge;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setExtraCharge(double extraCharge) {
		this.extraCharge = extraCharge;
	}
    
    public double getExtraCharge() {
		return extraCharge;
	}
    
    public void setMinimumCharge(double minimumCharge) {
		this.minimumCharge = minimumCharge;
	}
    
    public double getMinimumCharge() {
		return minimumCharge;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public int getId() {
		return id;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public String getName() {
		return name;
	}
}

package com.chengh.db.entity;

import java.util.Date;

/**
 * @program: ShardingJdbcDemo
 * @description:
 * @author: chengh
 * @create: 2019-09-05 15:04
 */
public class OrderInfo {

    private Long id;

    private Long userId;

    private String name;

    private Double payMoney;

    private Date payTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "OrderInfo{" + "id=" + id + ", userId=" + userId + ", name='" + name + '\'' + ", payMoney=" + payMoney
                + ", payTime=" + payTime + '}';
    }
}

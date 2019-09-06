package com.chengh.db.entity;

import java.util.Date;

/**
 * @program: ShardingJdbcDemo
 * @description: 订单
 * @author: chengh
 * @create: 2019-09-05 14:44
 */
public class Order {

    private Long id;

    private Long userId;

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
        return "Order{" + "id=" + id + ", userId=" + userId + ", payMoney=" + payMoney + ", payTime=" + payTime + '}';
    }
}

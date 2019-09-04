package com.chengh.db.entity;

import java.io.Serializable;

/**
 * @program: ShardingJdbcDemo
 * @description: 商品
 * @author: chengh
 * @create: 2019-09-03 16:56
 */
public class Goods implements Serializable {

    private Long id;

    private String name;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

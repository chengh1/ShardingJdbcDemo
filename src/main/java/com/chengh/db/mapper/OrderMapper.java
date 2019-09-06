package com.chengh.db.mapper;

import com.chengh.db.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import com.chengh.db.entity.Order;
import com.chengh.db.entity.User;

@Mapper
public interface OrderMapper {
    /**
     * 保存
     */
    void save(Order order);

    /**
     * 查询
     * @param id
     * @return
     */
    Order getById(Long id);

    OrderInfo getOrderInfoByUserId(Long userId);

}


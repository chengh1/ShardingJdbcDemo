package com.chengh.db.mapper;

import java.util.List;

import com.chengh.db.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chengh.db.entity.User;

@Mapper
public interface GoodsMapper {

    /**
     * 查询
     * @param id
     * @return
     */
    List<Goods> getById(@Param("id") Long id);
}


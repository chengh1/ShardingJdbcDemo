package com.chengh.db.mapper;

import com.chengh.db.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 保存
     */
    void save(User user);

    /**
     * 查询
     * @param id
     * @return
     */
    User getById(@Param("id")Long id);

    /**
     *
     * @param id
     * @return
     */
    List<User> getByIds(@Param("ids")List<Long> id);
}


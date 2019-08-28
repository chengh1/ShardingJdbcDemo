package com.chengh.db.entity;

import java.util.Date;

/**
 * @program: ShardingJdbcDemo
 * @description: 用户
 * @author: chengh
 * @create: 2019-06-28 11:31
 */
public class User {

    private Long id;

    private String name;

    private String phone;

    private String email;

    private String password;

    private Integer sex;

    private Date createTime;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", email='" + email + '\''
                + ", password='" + password + '\'' + ", sex='" + sex + '\'' + ", createTime=" + createTime + '}';
    }
}

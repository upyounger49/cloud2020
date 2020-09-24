package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {
    //dao层操作数据库的接口 读写两个操作
    public int add(Payment payment);

    public Payment getPaymentById(@Param("id") long id);
}

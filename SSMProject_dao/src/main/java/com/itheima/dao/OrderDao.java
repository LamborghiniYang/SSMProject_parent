package com.itheima.dao;

import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {
    /**
     * 查询所有订单
     * @return
     */
    @Select("select * from orders")
   /* @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "productId",property = "product",javaType = Product.class,
            one=@One(select = "com.itheima.dao.ProductDao.updateProduct"))
    })*/
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "productId",property = "product",javaType = Product.class,
                    one=@One(select = "com.itheima.dao.ProductDao.updateProduct") )
    })
    List<Orders> findAllOrder();
}

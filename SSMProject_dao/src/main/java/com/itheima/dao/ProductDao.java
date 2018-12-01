package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductDao {
    /**
     * 查询所有产品
     * @return
     */
    @Select("select * from product order by id asc")
    public List<Product> findAllProduct();

    /**
     * 添加商品信息
     * @param product
     */
    @Insert("insert into product values(com_sequence.nextval," +
            "#{productNum},#{productName},#{cityName},#{departureTime}," +
            "#{productPrice},#{productDesc},#{productStatus})")
    void addProduct(Product product);

    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product updateProduct(Integer id);

    /**
     * 更新商品信息
     * @param product
     */
    @Update("update product set productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} where id=#{id}")
    void updateProductInfo(Product product);

    /**
     * 删除商品信息
     * @param id
     */
    @Delete("delete from product where id=#{id}")
    void deleteProductById(Integer id);
}

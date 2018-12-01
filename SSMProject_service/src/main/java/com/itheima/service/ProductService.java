package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;

public interface ProductService {
    /**
     * 查询所有商品
     * @return
     * @param pageNum
     * @param pageSize
     */
    public PageInfo<Product> findAllProduct(int pageNum, int pageSize);

    /**
     * 添加商品
     * @param product
     */

    void addProduct(Product product);

    /**
     * 查询商品
     * @param id
     * @return
     */

    Product updateProduct(Integer id);

    /**
     * 更新商品信息
     * @param product
     */

    void updateProductInfo(Product product);

    /**
     * 删除商品
     * @param id
     */

    void deleteProductById(Integer id);


}

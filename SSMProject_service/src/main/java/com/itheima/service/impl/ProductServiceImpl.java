package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    /**
     * 查询所有商品
     * @return
     * @param pageNum
     * @param pageSize
     */

    @Override
    public PageInfo<Product> findAllProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
// 后面必须跟着查询所有数据的方法（注意，不是分页的方法，是查询所有数据的方法）        
        List<Product> productList = productDao.findAllProduct();
        System.out.println(productList);
// 创建PageInfo类        
        PageInfo<Product> pageInfo=new PageInfo<Product>(productList);
        return pageInfo;
    }

    /**
     * 添加商品
     * @param product
     */

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);

    }

    /**
     * 查询商品信息，通过id
     * @param id
     * @return
     */

    @Override
    public Product updateProduct(Integer id) {
       return productDao.updateProduct(id);
    }

    /**
     * 更新商品信息
     * @param product
     */

    @Override
    public void updateProductInfo(Product product) {
        productDao.updateProductInfo(product);
    }

    /**
     * 删除商品信息
     * @param id
     */
    @Override
    public void deleteProductById(Integer id) {
        productDao.deleteProductById(id);
    }
}

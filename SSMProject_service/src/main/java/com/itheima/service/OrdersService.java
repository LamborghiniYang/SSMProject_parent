package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;

public interface OrdersService {
    /**
     * 查询所有商品
     * @return
     * @param pageNum
     * @param pageSize
     */
    PageInfo<Orders> findAllOrder(int pageNum, int pageSize);
}

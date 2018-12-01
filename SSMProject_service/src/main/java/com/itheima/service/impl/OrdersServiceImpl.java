package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderDao orderDao;

    /**
     * 查询所有商品
     * @return
     * @param pageNum
     * @param
     */
    @Override
    public PageInfo<Orders> findAllOrder(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Orders> ordersList = orderDao.findAllOrder();
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersList);
        System.out.println(pageInfo);
        return pageInfo;

    }
}

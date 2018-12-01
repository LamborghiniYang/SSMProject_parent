package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("Orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("findAllOrder")
    public String findAllOrder(Model model, @RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize){
        PageInfo<Orders> ordersListPage= ordersService.findAllOrder(pageNum,pageNum);
        System.out.println(ordersListPage);
        model.addAttribute("ordersListPage",ordersListPage);
        return "order/ordersList";
    }
}

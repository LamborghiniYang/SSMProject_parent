package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 查询所有商品
     *
     * @param
     * @return
     */

    @RequestMapping("findAllProduct")
    public ModelAndView findAllProduct(@RequestParam(required = true, defaultValue = "1") int pageNum, @RequestParam(required = true, defaultValue = "5") int pageSize) {
        ModelAndView mv = new ModelAndView();
        //System.out.println("saasss");
        int i=1/0;
        PageInfo<Product> page = productService.findAllProduct(pageNum, pageSize);
        mv.setViewName("product/productList");
        mv.addObject("page", page);
        return mv;

    }

    /**
     * 添加商品信息的前台跳转到后台添加信息模板
     *
     * @return
     */
    @RequestMapping("productAdd")
    public String productAdd() {
        return "product/productAdd";

    }

    /**
     * 在添加商品信息模板，添加成功后，重定向到查询所有商品列表
     *
     * @param product
     * @return
     */
    @RequestMapping("addProduct")
    public String addProduct(Product product) {
        System.out.println(product);
        productService.addProduct(product);
        return "redirect:/Product/findAllProduct";
    }

    /**
     * 通过id，查询商品信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateProduct")
    public String updateProduct(Integer id, Model model) {
        Product product = productService.updateProduct(id);
        model.addAttribute("product", product);
        return "product/productUpdate";
    }

    /**
     * 更新商品信息，重定向到所有商品列表页
     *
     * @param product
     * @return
     */
    @RequestMapping("updateProductInfo")
    public String updateProductInfo(Product product) {
        productService.updateProductInfo(product);

        return "redirect:/Product/findAllProduct";
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteProductById")
    public String deleteProductById(Integer id) {
        productService.deleteProductById(id);
        return "redirect:/Product/findAllProduct";

    }
}

package com.qed.controllers;

import com.qed.common.BaseController;
import com.qed.common.EyeMsg;
import com.qed.event.EyeQueryProductEvent;
import com.qed.service.OrderService;
import com.qed.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * Created by trimup on 2016/6/29.
 */
@RestController
@RequestMapping(value = "p2peye/product")
public class ProductController  extends BaseController {


    private static final Logger L = LoggerFactory.getLogger(ProductController.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Autowired
    private ProductService productService;

    //平台名称
    @Value("${PRODUCT_TYPE}")
    private String allProduct_type;

    //项目开始时间
    @Value("${PRODUCT_START_TIME}")
    private String product_start_time;

    //项目结束时间
    @Value("${PRODUCT_END_TIME}")
    private String product_end_time;


    @Cacheable
    @RequestMapping(value = "/queryProduct", method = RequestMethod.GET)
    public EyeMsg queryProdcut(EyeQueryProductEvent event){

        //对参数做限制
        if(event.orEmpty())
            return new EyeMsg(EyeMsg.FAIL,"参数错误");

        event.setAllProduct_type(allProduct_type);
        event.setProduct_start_time(product_start_time);
        event.setProduct_end_time(product_end_time);
        EyeMsg msg =productService.queryEyeRequestProduct(event);
        return  msg;
    }




}

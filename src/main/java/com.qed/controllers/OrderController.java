package com.qed.controllers;

import com.qed.common.BaseController;
import com.qed.common.EyeMsg;
import com.qed.event.EyeQueryInvestEvent;
import com.qed.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * Created by trimup on 2016/6/30.
 */
@RestController
@RequestMapping(value = "p2peye/order")
public class OrderController  extends BaseController {



    private static final Logger L = LoggerFactory.getLogger(OrderController.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Autowired
    private OrderService orderService;




    @Cacheable
    @RequestMapping(value = "/queryOrderByPro", method = RequestMethod.GET)
    public EyeMsg queryOrderByPro(EyeQueryInvestEvent event){

        //对参数做限制
        if(event.orEmpty())
            return new EyeMsg(EyeMsg.FAIL,"参数错误");

        EyeMsg msg =orderService.getOrderListByPro(event);
        return  msg;
    }
}

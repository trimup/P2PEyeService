package com.qed.controllers;

import com.qed.common.EyeMsg;
import com.qed.common.EyeTokenMsg;
import com.qed.event.CreateEyeTokenEvent;
import com.qed.event.EyeCreateTokenEvent;
import com.qed.event.EyeQueryProductEvent;
import com.qed.service.EyeAccountService;
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
@RequestMapping(value = "p2peye/eyeAccount")
public class EyeAccountController {

    private static final Logger L = LoggerFactory.getLogger(EyeAccountController.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    EyeAccountService eyeAccountService;


    @Cacheable
    @RequestMapping(value = "/createToken", method = RequestMethod.GET)
    public EyeTokenMsg createToken(CreateEyeTokenEvent event) throws Exception {

        //对参数做限制
        if(event.orEmpty())
            return new EyeTokenMsg(EyeMsg.FAIL,new EyeCreateTokenEvent());
        EyeTokenMsg msg =eyeAccountService.createEyeAccountToken(event);
        return  msg;
    }

}

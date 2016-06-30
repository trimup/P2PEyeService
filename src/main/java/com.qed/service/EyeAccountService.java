package com.qed.service;

import com.qed.common.EyeTokenMsg;
import com.qed.entity.EyeAccountInfo;
import com.qed.event.CreateEyeTokenEvent;
import com.qed.event.EyeCreateTokenEvent;
import com.qed.persistence.EyeAccountMapper;
import com.qed.utils.MD5;
import com.qed.utils.RandomStringUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by trimup on 2016/6/30.
 */
@Service
public class EyeAccountService {


    private static final Logger l = LoggerFactory.getLogger(EyeAccountService.class);
    private static final SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    @Value("${EYE_ACCOUNT}")
    private String  eyeAccount;
    @Autowired
    private EyeAccountMapper eyeAccountMapper ;

    public EyeTokenMsg  createEyeAccountToken(CreateEyeTokenEvent event) throws Exception {
        //根据账户查询出
        EyeCreateTokenEvent data =new EyeCreateTokenEvent();
        EyeAccountInfo  eyeAccountInfo =eyeAccountMapper.queryEyeAccount(event.getUsername());
        if(eyeAccountInfo==null)
            return new EyeTokenMsg(EyeTokenMsg.FAIL,data);
        //验证帐号
        if(!MD5.sign(eyeAccountInfo.getPassword(),"utf-8").equals(event.getPassword()))
            return new EyeTokenMsg(EyeTokenMsg.FAIL,data);
        //生成token  更新到 用户名表中
        String token = RandomStringUntil.generateString(18);
        eyeAccountMapper.updateEyeAccountToken(token,event.getUsername());

        data.setToken(token);
        EyeTokenMsg tokenMsg =new EyeTokenMsg();
        tokenMsg.setData(data);
        tokenMsg.setResult(EyeTokenMsg.SUCCESS);
        return  tokenMsg;
    }


    public boolean  checkEyeToken(String token){
        EyeAccountInfo  eyeAccountInfo =eyeAccountMapper.queryEyeAccount(eyeAccount);
        if(eyeAccountInfo==null)
            return false;
        if(!eyeAccountInfo.getToken().equals(token))
            return false;
        if(new Date().getTime() - eyeAccountInfo.getUpdate_time().getTime() > 7200000)
            return false;
        return true;

    }



}

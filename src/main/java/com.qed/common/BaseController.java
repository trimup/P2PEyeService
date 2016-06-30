package com.qed.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {

    private static final Logger l = LoggerFactory.getLogger(BaseController.class);

    /**
     * 错误统一返回
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public EyeMsg exception(Exception e) {
        l.error(e.getMessage(), e);
        e.printStackTrace();
        EyeMsg msg =new EyeMsg();
        msg.setResult_code(EyeMsg.FAIL);
        msg.setResult_msg("未授权的访问!");
        return msg;
    }

}

package com.qed.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by trimup on 2016/6/30.
 */
@Data
public class EyeAccountInfo {

    private String  id    ;       //     INT(11)        NOT NULL AUTO_INCREMENT,
    private String  user_name  ;  // ARCHAR(128) DEFAULT NULL COMMENT '用户名',
    private String  password   ;  // RCHAR(128)  COMMENT '帐号密码',
    private String  token     ;   // AR(128)  COMMENT 'token',
    private Date update_time ; //     TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'token 更新时间',

}

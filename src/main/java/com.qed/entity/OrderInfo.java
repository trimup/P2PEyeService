package com.qed.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by leo on 7/9/15.
 */
@Data public class OrderInfo {

    private long id;

    private Integer order_user_tid;

    private Date pay_time;

    private Date create_time;

    private Long order_product_tid;

    private BigDecimal order_money;

    private Integer order_source;

    private String order_status_id;

    //    private double normal_rate;

    private long time_limit;

    //    private int orderNo;
    
    //协议编号
    private String agreement_uuid;
    //收益到期时间 
	private Date expect_payment_date;
	
	
	private Long transfered_order_tid;
	
	private Long transfered_product_tid;


    private Integer coupon_number; //加息券券码


}

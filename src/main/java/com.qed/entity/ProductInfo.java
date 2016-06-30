/**
 * @Version 1.0.0
 * Copyright (c) 2016上海相诚金融-版权所有
 */
package com.qed.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Class ProductInfo
 * @Description 项目信息类
 * @Author 张超超
 * @Date 2016/3/25 10:16
 */
@Data
@ToString
public class ProductInfo {
    private Long id;
    private String product_name; //项目名称
    private String creditor_name; //债权出让人姓名
    private String product_states_id;//项目状态
    private String product_type_id;//项目类型
    private Double collect_count; //计划募资金额
    private Double normal_rate;   //借款年利率
    private int time_limit;   //借款期限
    private String creditor_card_number;//债权出让人身份证号码
    private Integer proceeds_of_payment_id;//还款方式
    private String  create_time;
    private String  raise_end_time;


}

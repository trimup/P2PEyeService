package com.qed.event;

import com.google.common.base.Strings;
import lombok.Data;

/**
 * Created by trimup on 2016/6/29.
 *
 * 网络天眼查询 项目事件
 *
 */
@Data
public class EyeQueryProductEvent {

   private  Integer  status	;      //	标的状态:0.正在投标中的借款标;1.已完成(包括还款中和已完成的借款标).
   private  String time_from; //	String	Yes	起始时间如:2014-05-09 06:10:00, 状态为1是对应平台满标字段的值检索,状态为0就以平台发标时间字段检索.
   private  String time_to;	//	Yes	截止时间如:2014-05-09 06:10:00,     状态为1是对应平台满标字段的值检索,状态为0就以平台发标时间字段检索.
   private  Integer  page_size ;  //     每页记录条数.
   private  Integer page_index ;//	请求的页码.
   private  String   token;	//	请求 token 链接平台返回的秘钥或签名.
   private  String  product_start_time; //企额贷内部自己限制
   private  String  product_end_time; //企额贷内部自己限制
    private String  allProduct_type ; //企额贷内部自己限制


    public boolean orEmpty(){
        return  status ==null|| Strings.isNullOrEmpty(time_from)||Strings.isNullOrEmpty(time_to)||
                page_size==null||page_index==null||(status !=1&&status!=0);
    }
}

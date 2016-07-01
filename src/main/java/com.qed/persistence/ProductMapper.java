package com.qed.persistence;

import com.qed.entity.ProductInfo;
import com.qed.event.EyeQueryProductEvent;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by trimup on 2016/6/29.
 */
public interface ProductMapper {

    /**
     * 查询 网络天眼平台需要的  项目信息
     * @return
     */
    @Select({"<script> select id,product_name,creditor_name, " +
            "product_states_id,collect_count,real_collect_count,good_rate,bad_rate, " +
            "normal_rate ,time_limit ,date_format(raise_end_time,'%Y-%m-%d %h:%i:%s') raise_end_time," +
            "date_format(create_time,'%Y-%m-%d %h:%i:%s') create_time ,proceeds_of_payment_id from product_info <where> " +
            "<if test='status !=null'>  <choose> " +
            " <when test ='status == 0'> and create_time &gt;=#{time_from}  and create_time &lt;=#{time_to} </when>" +
            " <when test ='status == 1'> and  raise_end_time &gt;=#{time_to}  and raise_end_time &lt;=#{time_to} </when>" +
            " </choose> </if> " +
            "<if test='product_start_time !=null'>  and create_time &gt;=#{product_start_time} </if> "+
            "<if test='product_end_time !=null'>  and create_time &lt;= #{product_end_time} </if> "+
            "<if test='allProduct_type !=null'>  and product_type_id in (${allProduct_type}) </if> "+
            " </where> </script>"})
    public List<ProductInfo>  queryEyeProdcutInfo(EyeQueryProductEvent event);





}

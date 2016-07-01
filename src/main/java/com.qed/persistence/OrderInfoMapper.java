package com.qed.persistence;

import com.qed.entity.OrderInfo;
import com.qed.entity.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by trimup on 2016/6/30.
 */
public interface OrderInfoMapper {


    //根据项目查询已支付订单
    @Select({"<script> select * from order_info where order_product_tid in" +
            "<foreach collection='list' item='item' index='index' open = '(' separator=',' close=')'>"+
            "#{item.id}"+
            "</foreach> and (order_status_id=100565 OR order_status_id=100567 OR order_status_id=100617)  "+
            " </script>"})
    public List<OrderInfo> selectOrderByPro(List<ProductInfo> productInfos);

    //根据项目id已支付订单
    @Select({"<script> select * from order_info where order_product_tid =#{order_product_tid} " +
            "and (order_status_id=100565 OR order_status_id=100567 OR order_status_id=100617)  "+
            " </script>"})
    public List<OrderInfo> selectOrderByProID(@Param("order_product_tid") String order_product_tid);



}

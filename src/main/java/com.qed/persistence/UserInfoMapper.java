package com.qed.persistence;

import com.qed.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by trimup on 2016/6/29.
 */
public interface UserInfoMapper {


    /**
     * 查询 项目的债权出人信息
     * @return
     */
    @Select("select * from user_info where identity_card in (select creditor_card_number from product_info)")
    public List<UserInfo>  selectUserByProductCreditor();


    /**
     * 查询出 购买某项目的 人员信息
     */
    @Select("SELECT * FROM user_info WHERE id IN (SELECT  order_user_tid FROM order_info  " +
            "o WHERE o.`order_product_tid` =#{order_product_tid} )")
    public List<UserInfo>  selectUserByProId(@Param("order_product_tid") String order_product_tid);
}

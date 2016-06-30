package com.qed.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by leo on 7/9/15.
 */
@Data @ToString public class UserInfo {

    private Integer id;
    private String real_name;
    private String user_name;
    private String identity_card;
    private String telephone;
    private BigDecimal account;//账户总金额＝可用＋冻结
    private Date create_time;
    private String source_type;//注册来源
    private String sourceType ;//注册来源
    private String register_channel;//注册渠道
    private Integer binding_verify_status;
    private Integer isfrozen;//是否冻结 0 非冻结 1 冻结
    private long referrer_id  =-1;//推荐人的ID
    private String consultant;
    private String area;
    private Long user_account_tid;//银行卡ID
    private BigDecimal cash;//可用余额
    private BigDecimal frozen_money;//冻结
    private Integer user_level_id;//用户等级
    private String user_level;//用户等级
    private String picked_user_count;//有效推荐人数
    private String amount_per_day ;// 日均投资金额
    private String province;//省份；
    private String city;//城市；
    private String rreal_name;
    private Integer isInvest;
    private Integer errortips;
    private Date error_time;

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserInfo))
            return false;

        UserInfo that = (UserInfo) o;
        return id==that.id;

    }

    @Override public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (int) (getReferrer_id() ^ (getReferrer_id() >>> 32));
        result = 31 * result + (getConsultant() != null ? getConsultant().hashCode() : 0);
        result = 31 * result + (getArea() != null ? getArea().hashCode() : 0);
        result =
            31 * result + (getUser_account_tid() != null ? getUser_account_tid().hashCode() : 0);
        result = 31 * result + (getCash() != null ? getCash().hashCode() : 0);
        result = 31 * result + (getFrozen_money() != null ? getFrozen_money().hashCode() : 0);
        result = 31 * result + (getUser_level_id() != null ? getUser_level_id().hashCode() : 0);
        result = 31 * result + (getUser_level() != null ? getUser_level().hashCode() : 0);
        result =
            31 * result + (getPicked_user_count() != null ? getPicked_user_count().hashCode() : 0);
        result = 31 * result + (getAmount_per_day() != null ? getAmount_per_day().hashCode() : 0);
        result = 31 * result + (getProvince() != null ? getProvince().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getRreal_name() != null ? getRreal_name().hashCode() : 0);
        result = 31 * result + (getIsInvest() != null ? getIsInvest().hashCode() : 0);
        result = 31 * result + (getErrortips() != null ? getErrortips().hashCode() : 0);
        return result;
    }






}

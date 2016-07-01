package com.qed.pojo;

import com.qed.utils.Base64;
import com.qed.utils.DESUntil;
import lombok.Data;

/**
 * Created by trimup on 2016/6/29.
 */
@Data
public class EyeProductPojo {

            private String id	         ;   // String	Yes	标编号	标的唯一编号(不为空,很重要)
            private String platform_name ;	// String	Yes	平台名称	平台中文名称.
            private String url	         ;   // String	Yes	标的链接	借款标的URL链接.
            private String title	     ;   // String	Yes	标题	标的标题信息.
            private String username	     ;   // String	Yes	用户名	借款人(发标人)的用户名称,如果没有借款人用户名,一定要返回下面的 userid,尽量不为空.
            private String userid	     ;   // String	Yes	用户编号	发标人的用户编号/ID.
            private int status	     ;   // Int	Yes	标的状态	0,正在投标中的借款标;1,已完成(包括还款中和已完成的借款标).
            private int c_type	     ;   // Int	Yes	借款类型	0 代表信用标,1 担保标;2 抵押,质押标, 3 秒标; 4 债权转让标(流转标,二级市场标的);5 理财计划(宝类业务_活期);
                                            // 6 其它;7 净值标;8 活动标(体验标).9 理财计划(宝类业务_定期).
                                           // 3，4，5标类型不参与贷款余额计算；请注意5【理财计划(宝类业务_活期)】和9【理财计划(宝类业务_定期)】的区分；4债权转让标指的是不会产生新待还的转让，如果会产生新待还，请返回其他标类型.
            private Double amount	     ;   // Double	Yes	借款金额	以元为单位,精度2位(1000.00),如万元请转换为元,请过滤掉借款金额小于50块的标.
            private Double rate	         ;   // Double	Yes	借款年利率	如果为月利率或天利率,统一转换为年利率并使用小数表示;精度4位,如:0.0910.
            private int  period	     ;   // Int	Yes	借款期限	借款期限的数字。如3月这里只返回3若借款标的为流转标,对应的要有流转期限.
            private int  p_type	     ;   // Int	Yes	期限类型	0 代表天,1 代表月.
            private int  pay_way	     ;   // Int	Yes	还款方式	0 代表其他;1 按月等额本息还款;2按月付息,到期还本;
            private Double process        ;    // 	Double	Yes	完成百分比	转换成小数表示.
            private Double reward	        ;  //      Double	No	投标奖励	如奖励为百分比,使用小数表示.
            private Double guarantee	   ;   //      Double	No	担保奖励	如奖励为百分比,使用小数表示.
            private String start_time	   ;   //      Double	Yes	标的创建时间	格式如:2013-08-10 14:24:01(24小时制).
            private String end_time	       ;   //      String	状态1:Yes,状态0:No	满标时间(标的放款时间，标的起息时间)	格式如:2013-08-10 13:10:00标的放款时间，标的起息时间，如果没有起息时间，请提供投资记录最后一笔的投资时间，请不要理解为标最后的的还款完成日期.
            private int  invest_num	  ;    //      Int	Yes	投资次数	这笔借款标有多少个投标记录.
            private Double c_reward	     ;     //      Double	No	续投奖励	继续投标的奖励.


    /**
     * 标的加密
     * @return
     */
           public String getId(){
               return Base64.encode(DESUntil.encrypt(id,"51qedqwer@sameway"));
           }


}

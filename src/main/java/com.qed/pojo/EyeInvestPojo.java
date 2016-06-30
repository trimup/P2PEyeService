package com.qed.pojo;

import lombok.Data;

/**
 * Created by trimup on 2016/6/30.
 */
@Data
public class EyeInvestPojo {
    private  String     id	         ;   //  String	Yes	标编号	标的唯一编号(不为空,很重要)
    private  String     link	     ;   //  String	No	标的链接	URL链接.
    private  String     useraddress  ;   //  String	No	用户所在地	投标人所在城市.
    private  String     username	 ;   //  String	Yes	用户名	投标人的用户名称,登录账号,可辨识区分,可支持加密数据.
    private  String     userid	     ;   //  String	Yes	用户编号	投标人的用户编号/ID.
    private  String     type	     ;   //  String	No	投标方式	例如:手动、自动.
    private  Double     money	     ;   //  Double	No	投标金额	投标金额实际生效部分(保留两位小数).
    private  Double     account	     ;   //  Double	Yes	有效金额	投标金额实际生效部分(保留两位小数),请过滤掉投资金额小于10块的记录.
    private  String     status	     ;   //  String	No	投标状态	例如:成功、部分成功、失败.
    private  String     add_time	 ;   //  String	Yes	投标时间	格式如:2014-03-13 16:44:26.
}

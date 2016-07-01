package com.qed.event;

import com.google.common.base.Strings;
import com.qed.utils.Base64;
import com.qed.utils.DESUntil;
import lombok.Data;

/**
 * Created by trimup on 2016/6/30.
 *
 * 请求用户投资 信息Event
 */
@Data
public class EyeQueryInvestEvent {

    private String  id	      ;    // String	Yes	标的编号.
    private Integer  page_size;    //	Int	Yes	每页记录条数.
    private Integer  page_index;    //	Int	Yes	请求的页码.
    private String  token    ;    //	String	No	请求 token 链接平台返回的秘钥或签名.


    // 标的id 解密
    public String getId(){
        return DESUntil.decrypt(Base64.decode(id),"51qedqwer@sameway");
    }


    public boolean orEmpty(){
        return Strings.isNullOrEmpty(id)||page_size==null||page_index==null||Strings.isNullOrEmpty(token);
    }
}

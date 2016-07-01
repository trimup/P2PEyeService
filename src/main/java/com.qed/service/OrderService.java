package com.qed.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qed.common.EyeMsg;
import com.qed.entity.OrderInfo;
import com.qed.entity.UserAttributeInfo;
import com.qed.entity.UserInfo;
import com.qed.event.EyeQueryInvestEvent;
import com.qed.persistence.OrderInfoMapper;
import com.qed.persistence.UserAttributeInfoMapper;
import com.qed.persistence.UserInfoMapper;
import com.qed.pojo.EyeInvestPojo;
import com.qed.utils.MD5;
import com.qed.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by trimup on 2016/6/30.
 */

@Service
public class OrderService {

    private static final Logger l = LoggerFactory.getLogger(ProductService.class);


    private static final SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private UserAttributeInfoMapper userAttributeInfoMapper;

    @Autowired
    private UserInfoMapper  userInfoMapper;


    //项目URL
    @Value("${PRODUCT_URL}")
    private String PRODUCT_URL;

    /**
     * 根据项目id 查询订单信息
     * @return
     */
    public EyeMsg   getOrderListByPro(EyeQueryInvestEvent event) throws Exception{

        EyeMsg eyeMsg =new EyeMsg();


        PageHelper.startPage(event.getPage_index(),event.getPage_size());
        PageHelper.orderBy("create_time desc");
        //根据项目 查询出 已完成的订单
        List<OrderInfo> orderInfos =orderInfoMapper.selectOrderByProID(event.getId());


        if(null==orderInfos||orderInfos.isEmpty())
            return new EyeMsg(EyeMsg.FAIL,EyeMsg.FAIL_MSG,0,0);

        //查询该用户的用户信息
        List<UserInfo> userInfos =userInfoMapper.selectUserByProId(event.getId());
        //根据订单list 查询出所在地
        List<UserAttributeInfo> userAttributeInfos =userAttributeInfoMapper.getUserAttributeInfoById(orderInfos);
        Map<Integer,String> userAttrMap = userAttributeInfos.stream().
                collect(Collectors.toMap(UserAttributeInfo::getUser_tid,u->u.getProvince()+"-"+u.getCity()));
        //用户map
        Map<Integer,UserInfo> userMap = userInfos.stream().
                collect(Collectors.toMap(UserInfo::getId,u->u));
        List<EyeInvestPojo> investPojos =new ArrayList<>();
        for(OrderInfo o :orderInfos){
            EyeInvestPojo  ei =new EyeInvestPojo();
            //项目id
            ei.setId(o.getOrder_product_tid().toString());
            //  项目url  连接地址
            ei.setLink(String.format(PRODUCT_URL,o.getOrder_product_tid()));
            // 用户所在地
            ei.setUseraddress(userAttrMap.get(o.getOrder_user_tid()));
            //用户名
            UserInfo user=userMap.get(o.getOrder_user_tid());
            ei.setUsername(user.getTelephone().substring(0,3)+"****"+user.getTelephone().substring(7));
            ei.setUserid(MD5.sign(user.getTelephone(),user.getIdentity_card(),"utf-8"));
            ei.setType("手动");
            //投标金额
            ei.setMoney(o.getOrder_money().doubleValue());
            //有效金额
            ei.setAccount(o.getOrder_money().doubleValue());
            //订单时间
            ei.setAdd_time(Format.format(o.getPay_time()));
            investPojos.add(ei);
        }
        long totalCount =
                event.getPage_size() > 0 ? ((Page) orderInfos).getTotal() : orderInfos.size();

        //信息
        eyeMsg.setLoans(investPojos);
        //返回 成功失败参数
        eyeMsg.setResult_code(EyeMsg.SUCCESS);
        //总页数
        eyeMsg.setPage_count(NumberUtil.Division(event.getPage_size(), totalCount));
        //页码
        eyeMsg.setPage_index(event.getPage_index());
        //成功信息
        eyeMsg.setResult_msg(EyeMsg.SUCCESS_MSG);

        return  eyeMsg;

    }


}

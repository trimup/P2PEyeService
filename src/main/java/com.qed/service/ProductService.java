package com.qed.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qed.common.EyeMsg;
import com.qed.common.EyeProductEnums;
import com.qed.entity.OrderInfo;
import com.qed.persistence.OrderInfoMapper;
import com.qed.pojo.EyeProductPojo;
import com.qed.entity.ProductInfo;
import com.qed.entity.UserInfo;
import com.qed.event.EyeQueryProductEvent;
import com.qed.persistence.ProductMapper;
import com.qed.persistence.UserInfoMapper;
import com.qed.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by trimup on 2016/6/29.
 */
@Service
public class ProductService {

    private static final Logger l = LoggerFactory.getLogger(ProductService.class);



    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;


    //平台名称
    @Value("${PLATFORM_NAME}")
    private String PLATFORM_NAME;

    //项目URL
    @Value("${PRODUCT_URL}")
    private String PRODUCT_URL;



    /**
     * 根据网络天眼平台传送过来的参数  查询项目
     * @param event
     */
    public EyeMsg queryEyeRequestProduct(EyeQueryProductEvent event){


        EyeMsg eyeMsg =new EyeMsg();

        //分页
        PageHelper.startPage(event.getPage_index(),event.getPage_size());
        PageHelper.orderBy("create_time desc");

        //从数据库中查询出 企额贷平台中符合条件的项目
        List<ProductInfo>  qedProList  =productMapper.queryEyeProdcutInfo(event);
        if(qedProList==null||qedProList.isEmpty())
            return new EyeMsg(EyeMsg.FAIL,EyeMsg.FAIL_MSG,0,0);


        //项目债权人 信息列表
        List<UserInfo>  prCreditorList =userInfoMapper.selectUserByProductCreditor();
        //从订单表中查询出所有该项目的订单
        List<OrderInfo> orderInfoList =orderInfoMapper.selectOrderByPro(qedProList);

        Map<String,Integer> prCreditorMap =prCreditorList.stream().
                collect(Collectors.toMap(UserInfo::getIdentity_card,UserInfo::getId));
        List<EyeProductPojo>  eyeProList=new ArrayList<>();
        //将企额贷平台的  项目信息转换成  网络天眼平台的信息
        for(ProductInfo p:qedProList){
            EyeProductPojo e =new EyeProductPojo();
            //项目ID  标的唯一编号
            e.setId(p.getId().toString());
            //平台名称
            e.setPlatform_name(PLATFORM_NAME);
            //标的链接
            e.setUrl(String.format(PRODUCT_URL, p.getId()));
            //标题
            e.setTitle(p.getProduct_name());
            //借款人(发标人)的用户名称
            e.setUsername(p.getCreditor_name());
            //借款人的用户id
            e.setUserid(prCreditorMap.get(p.getCreditor_card_number()==null?"":p.getCreditor_card_number()).toString());
            //标的状态
            e.setStatus(EyeProductEnums.ProductStatesEnum.MZZ.equals(p.getProduct_states_id())?0:1);
            //借款类型  新手特供和新手超级标的字段为 活动标 8  其他为2 （抵押,质押标）
            e.setC_type(EyeProductEnums.ProductTypeEnum.XSTG.equals(p.getProduct_type_id())||
                    EyeProductEnums.ProductTypeEnum.CJXSB.equals(p.getProduct_type_id())?8:2);
            //借款金额
            e.setAmount(p.getCollect_count().doubleValue());
            //借款年利率
            e.setRate(p.getNormal_rate());
            //借款期限
            e.setPeriod(p.getTime_limit());
            //期限类型 默认为天
            e.setP_type(0);
            //还款方式
            e.setPay_way(getPayWay(p.getProceeds_of_payment_id()));



            //获取该项目的所有订单金额总和

                  BigDecimal allOrderMoney= orderInfoList.stream().filter(
                            o-> o.getOrder_product_tid().equals(p.getId())).
                            map(u-> u.getOrder_money()).
                            reduce((a,b)->a.add(b)).orElse(new BigDecimal(0));
            //完成百分比  保留两位小数
            e.setProcess(allOrderMoney.divide(p.getCollect_count(),2,BigDecimal.ROUND_HALF_UP).doubleValue());
            //标的创建时间
            e.setStart_time(p.getCreate_time());
            //满标时间 只在请求状态为1的时候设置
            if(event.getStatus()==1)
            e.setEnd_time(p.getRaise_end_time());
            //投资次数
            e.setInvest_num((int)orderInfoList.stream().filter(
                    o-> o.getOrder_product_tid().equals(p.getId())).count());
            eyeProList.add(e);
        }

        long totalCount =
                event.getPage_size() > 0 ? ((Page) qedProList).getTotal() : qedProList.size();
        //信息
        eyeMsg.setLoans(eyeProList);
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


    /**
     * 还款方式 企额贷 平台 对应天眼
     * @param proceedsPayment
     * @return
     */
    private int getPayWay(Integer proceedsPayment){
        switch (proceedsPayment){
            case 100058:  // 先息后本（月）  ==>按月付息,到期还本;
                return 2;
            case 100065:   //到期本息 ==》 其他
                return 0;
            case 100057:  // 先息后本（季） ==》 按季付息,到期还本
                return 8;
            case 101214:  // 按月付息 ==》  其他
                return 0;
            case 101215:  // 按季付息 ==》  其他
                return 0;
            case 103035:  //  等额本息==》 按月等额本息还款
                return 1;
            case 103039:  //  等额本金 ==》为等额本金,按月还本金
                return 8;
            default:
                return 0;  //其他
        }
    }
}

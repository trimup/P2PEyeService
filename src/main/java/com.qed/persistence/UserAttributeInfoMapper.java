/**
 * 
 */
package com.qed.persistence;

import com.qed.entity.OrderInfo;
import com.qed.entity.UserAttributeInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xw37
 *
 */
public interface UserAttributeInfoMapper {

	@Select({"<script> SELECT DISTINCT user_tid,telephone,province,city,supplier FROM user_attribute_info WHERE user_tid in" +
			"<foreach collection='list' item='item' index='index' open = '(' separator=',' close=')'>"+
			"#{item.order_user_tid}"+
			"</foreach>  "+
			" </script>"})
	public List<UserAttributeInfo> getUserAttributeInfoById(List<OrderInfo> orderInfos);
}

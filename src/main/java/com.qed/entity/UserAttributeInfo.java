package com.qed.entity;

import lombok.Data;

/**
 * 用户所属区域信息
 * @author xw37
 *
 */
@Data
public class UserAttributeInfo {
	private Integer id;
	/** 关联用户ID */
	private Integer user_tid;
	/** 手机号码 */
	private String telephone;
	/** 省份 */
	private String province;
	/** 城市 */
	private String city;
	/** 运营商 */
	private String supplier;
}

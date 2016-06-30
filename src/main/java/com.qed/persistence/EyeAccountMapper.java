package com.qed.persistence;

import com.qed.entity.EyeAccountInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by trimup on 2016/6/30.
 */
public interface EyeAccountMapper {


    /**
     * 查询eyeaccount账户
     * @param user_name
     * @return
     */
    @Select(" select * from eye_account where user_name=#{user_name} ")
    public EyeAccountInfo queryEyeAccount(@Param("user_name") String user_name);


    @Update("update eye_account set token =#{token} where user_name=#{user_name}")
    public void updateEyeAccountToken(@Param("token") String token,@Param("user_name") String user_name);
}

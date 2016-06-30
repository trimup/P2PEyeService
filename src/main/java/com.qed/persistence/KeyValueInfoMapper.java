package com.qed.persistence;


import com.qed.entity.KeyValueInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by leo on 7/23/15.
 */
public interface KeyValueInfoMapper {


    @Select("SELECT * FROM keyvalue_map WHERE group_name = #{group_name} ")
    public List<KeyValueInfo> getKeyValuesByGroupName(String group_name);







}

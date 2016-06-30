package com.qed.service;

import com.qed.entity.KeyValueInfo;
import com.qed.persistence.KeyValueInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by trimup on 2016/6/29.
 */
@Service
public class KeyValueService {




    @Autowired
    private KeyValueInfoMapper keyValueInfoMapper;

    public Map<Integer,String> getBaseCode(String groupName)
    {
        List<KeyValueInfo> list = keyValueInfoMapper.getKeyValuesByGroupName(groupName);
        Map<Integer, String> result = new HashMap<>();
        list.forEach(
                keyValueInfo -> result.put(keyValueInfo.getId(),keyValueInfo.getValue_name()));
        return result;
    }
}

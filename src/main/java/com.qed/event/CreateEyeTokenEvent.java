package com.qed.event;

import com.google.common.base.Strings;
import lombok.Data;

/**
 * Created by trimup on 2016/6/30.
 */
@Data
public class CreateEyeTokenEvent {
    private String username	;//String	Yes	用户名.
    private String password	;//String	Yes	密码.

    public boolean orEmpty(){
        return Strings.isNullOrEmpty(username)||Strings.isNullOrEmpty(password);
    }
}

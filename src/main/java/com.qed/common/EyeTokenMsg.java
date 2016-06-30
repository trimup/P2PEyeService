package com.qed.common;

import lombok.Data;

/**
 * Created by trimup on 2016/6/30.
 */
@Data
public class EyeTokenMsg {
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    private int result;
    private Object data = "";

    public EyeTokenMsg(int i,Object data) {
        this.result = i;
        this.data =data;
    }

    public EyeTokenMsg() {

    }
}

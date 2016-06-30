package com.qed.common;

import lombok.Data;

/**
 * Created by trimup on 2016/6/29.
 */
@Data
public class EyeMsg {
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final String SUCCESS_MSG = "获取数据成功";
    public static final String FAIL_MSG = "未授权的访问!";
    private int result_code;
    private String result_msg;
    private int page_count;
    private int page_index;
    private Object loans = "";

    public EyeMsg(int i, String message) {
        this.result_code = i;
        this.result_msg = message;
    }

    public EyeMsg() {

    }
}

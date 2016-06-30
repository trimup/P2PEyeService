package com.qed.common;

/**
 * Created by trimup on 2016/6/29.
 *
 * 枚举类
 */
public class EyeProductEnums {


    /**
     * 体验项目+新手项目+体验项目+融资项目
     * @author xw37
     *
     */
    public enum ProductTypeEnum {
        /** 101165：本金保障 */
        BJBZ2("101165"),
        /** 101159:本金保障 */
        BJBZ("101159"),
        /** 101160:新手特供 */
        XSTG("101160"),
        /** 101223:体验项目 */
        TYXM("101223"),
        /** 103033:超级新手标（1K） */
        CJXSB("103033"),
        /** 103034:车辆抵押 */
        CLDY("103034");

        private final String value;

        ProductTypeEnum(String typeId) {
            this.value = typeId;
        }

        public String getValue() {
            return value;
        }
    }


    /**
     * 项目状态(顺序不要变动）
     * @author xw37
     *
     */
    public enum ProductStatesEnum {
        /** 募资中：100051 */
        MZZ("100051"),
        /** 进行中：100053 */
        JXZ("100053"),
        /** 已结束：100054 */
        YJS("100054"),
        /** 已退出：100056 */
        YTC("100056");

        private final String value;

        ProductStatesEnum(String typeId) {
            this.value = typeId;
        }

        public String getValue() {
            return value;
        }
    }





}

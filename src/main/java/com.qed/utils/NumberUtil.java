/**
 * @Version 1.0.0
 * Copyright (c) 2016上海相诚金融-版权所有
 */
package com.qed.utils;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NumberUtil {

    private final static String JX = "JX";

    private final static String TY = "TY";

    public static List<String> get16BitJXRandomStringList(int num) {

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < num; i++) {

            String uuid = UUID.randomUUID().toString();

            StringBuffer strbuf = new StringBuffer(NumberUtil.JX);

            strbuf.append(uuid.substring(0, 16).replaceAll("-", "").toUpperCase());

            result.add(strbuf.toString());

        }

        return result;

    }

    public static List<String> get16BitTYRandomStringList(int num) {

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < num; i++) {

            String uuid = UUID.randomUUID().toString();

            StringBuffer strbuf = new StringBuffer(NumberUtil.TY);

            strbuf.append(uuid.substring(0, 16).replaceAll("-", "").toUpperCase());

            result.add(strbuf.toString());

        }

        return result;

    }

    public static String transRate(String rate) {

        String[] rateArr = rate.split("\\|");

        String relust = "";

        if (2 == rateArr.length) {

            if ("B".equals(rateArr[1])) {

                BigDecimal rateBigDecimal = new BigDecimal(rateArr[0]);

                relust = rateBigDecimal.multiply(new BigDecimal("0.1")).toString();

            } else {

                relust = rateArr[0];

            }

        }

        return relust;
    }

    public static String format(Number number) {
        BigDecimal b = new BigDecimal(Double.toString(number.doubleValue()));
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
/*	
    public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		
		System.out.println(NumberUtil.get16BitRandomStringSet(10000).size());
		
		System.out.println(System.currentTimeMillis());
	}
	*/


    public static Integer Division(int Divisor, long Dividend) {
        if (Dividend % Divisor == 0) {
            return (int) Dividend / Divisor;
        } else {
            return (int) Dividend / Divisor + 1;
        }
    }


    public   static  String percent( double  p1,  double  p2)  {
        String str;
        double  p3  =  p1  /  p2;
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits( 2 );
        str  =  nf.format(p3);
        return  str;
    }
}

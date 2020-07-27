package me.myshop.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyBigDecimal {
    static DecimalFormat df = new DecimalFormat("0.00");

    //建议使用BigDecimal类型存储金钱或者其它对数字小数部分敏感的数据
    public static BigDecimal Convert(BigDecimal data) {
        return new BigDecimal(df.format(data));
    }

    /**
     * 不建议使用基本类型int、float、double或者它们的包装类存储金钱类数据，容易出现精度问题
     */
    public static BigDecimal Convert(int data) {
        return new BigDecimal(df.format(data));
    }

    public static BigDecimal Convert(float data) {
        return new BigDecimal(df.format(data));
    }

    public static BigDecimal Convert(double data) {
        return new BigDecimal(df.format(data));
    }
}

package com.core;

import java.math.BigDecimal;

public class Calc {

    public static String plus(String a, String b){
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.add(b1).toString();
    }

    public static String minus(String a, String b){
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.subtract(b1).toString();
    }

    public static String multiply(String a, String b){
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.multiply(b1).toString();
    }

    public static int compare(String a, String b){
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.compareTo(b1);
    }

}

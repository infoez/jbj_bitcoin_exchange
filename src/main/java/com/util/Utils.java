package com.util;

import java.security.MessageDigest;

public class Utils {

    public static String sha256(String base) throws Exception {

        StringBuffer hexString = new StringBuffer();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));

        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o){
        return o != null;
    }

    public static String isNull(String a, String b){
        if(a == null) {
            return b;
        } else {
            return a;
        }
    }

}

package me.myshop.common.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Base64;

public class EnhancePassword {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String coding(String password) {
        try {
            password = Base64.getEncoder().encodeToString(password.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decoding(String password) {
        try {
            byte[] bytes = Base64.getDecoder().decode(password);
            password = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}

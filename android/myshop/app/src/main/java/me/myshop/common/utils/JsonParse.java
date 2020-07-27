package me.myshop.common.utils;

import com.google.gson.Gson;

/**
 * 单例对象JsonParse，用于频繁的对象状态转换
 */
public class JsonParse {
    private volatile static JsonParse sJsonParse;
    private static Gson sGson = new Gson();

    private JsonParse() {
    }

    public static JsonParse getInstance() {

        if (sJsonParse == null) {

            synchronized (JsonParse.class) {

                if (sJsonParse == null) {

                    sJsonParse = new JsonParse();

                }

            }
        }

        return sJsonParse;
    }

    public static String toJson(Object src) {
        return sGson.toJson(src);
    }

    public static <T> T fromJson(String json,  Class<T> classOfT) {
        return sGson.fromJson(json, classOfT);
    }

}

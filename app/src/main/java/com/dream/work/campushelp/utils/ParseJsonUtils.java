package com.dream.work.campushelp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */

public class ParseJsonUtils {
    private static Gson gson = new GsonBuilder().create();

    public static <T> T getData(JsonElement jsonElement, Class<T> classOfT) {
        Object object = gson.fromJson(jsonElement, classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }

    public static <T> T getData(String jsonElement, Class<T> classOfT) {
        Object object = gson.fromJson(jsonElement, classOfT);
        return Primitives.wrap(classOfT).cast(object);
    }

    public static <T> List<T> getDataFromList(JsonElement jsonElement, Class<T> classOfT) {
        List<T> list = new ArrayList<>();
        for (JsonElement json : jsonElement.getAsJsonArray()) {
            list.add(getData(json, classOfT));
        }
        return list;
    }
}

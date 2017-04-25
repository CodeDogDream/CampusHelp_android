package com.dream.work.campushelp.network.bean;

import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/3/14.
 */

public class DataBean {
    private JsonElement allData;
    private int code;
    private String msg;
    private JsonElement data;

    public JsonElement getAllData() {
        return allData;
    }

    public void setAllData(JsonElement allData) {
        this.allData = allData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}

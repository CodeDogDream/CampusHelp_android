package com.dream.work.campushelp.network.netconvert;

import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.ParseJsonUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Dream on 2017/4/2.
 */

public class CampusConvert implements Converter<ResponseBody, DataBean> {
    private CampusConvert() {

    }

    public static CampusConvert create() {
        return new CampusConvert();
    }

    @Override
    public DataBean convert(ResponseBody value) throws IOException {
        String data = value.string();
        DataBean dataBean = ParseJsonUtils.getData(data, DataBean.class);
        dataBean.setAllData(new JsonParser().parse(data));
        return dataBean;
    }
}

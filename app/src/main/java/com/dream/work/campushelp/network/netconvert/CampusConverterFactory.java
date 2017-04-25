package com.dream.work.campushelp.network.netconvert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Dream on 2017/4/2.
 */

public class CampusConverterFactory extends Converter.Factory {
    public static CampusConverterFactory create() {
        return new CampusConverterFactory();
    }

    private CampusConverterFactory() {

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return CampusConvert.create();
    }
}

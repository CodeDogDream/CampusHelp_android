package com.dream.work.campushelp.utils;

import android.content.Context;

import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.dream.work.campushelp.Interface.ImageDownloadCallback;
import com.dream.work.campushelp.entity.LocationInfo;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dream on 2017/4/16.
 */

public class DownImageService implements Runnable {
    private ArrayList<String> urls;
    private ImageDownloadCallback callback;
    private Context context;
    private static DownImageService instant;
    private ArrayList<LatLng> location;

    public static DownImageService getInstance() {
        if (instant == null) {
            synchronized (DownImageService.class) {
                if (instant == null) {
                    instant = new DownImageService();
                }
            }
        }
        return instant;
    }


    private DownImageService() {

    }

    public DownImageService setData(Context context, ArrayList<String> urls, ImageDownloadCallback callback, ArrayList<LatLng> location) {
        this.urls = urls;
        this.callback = callback;
        this.context = context;
        this.location = location;
        return this;
    }

    @Override
    public void run() {
        ArrayList<LocationInfo> files = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            try {
                File file = Glide.with(context)
                        .load(urls.get(i))
                        .downloadOnly(200, 200)
                        .get();
                files.add(new LocationInfo(file, location.get(i)));
            } catch (Exception e) {
                e.printStackTrace();
                callback.onDownLoadFail();
            }
        }
        if (files.size() != 0) {
            callback.onDownLoadSuccess(files);
        } else {
            callback.onDownLoadFail();
        }
    }
}

package com.dream.work.campushelp.Interface;

import com.dream.work.campushelp.entity.LocationInfo;

import java.util.ArrayList;

/**
 * Created by Dream on 2017/4/16.
 */

public interface ImageDownloadCallback {
    void onDownLoadSuccess(ArrayList<LocationInfo> files);

    void onDownLoadFail();
}

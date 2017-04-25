package com.dream.work.campushelp.entity;

import com.baidu.mapapi.model.LatLng;

import java.io.File;

/**
 * Created by Dream on 2017/4/23.
 */

public class LocationInfo {
    public File file;
    public LatLng latLng;
    public String uid;

    public LocationInfo(File file, LatLng latLng) {
        this.file = file;
        this.latLng = latLng;
    }
}

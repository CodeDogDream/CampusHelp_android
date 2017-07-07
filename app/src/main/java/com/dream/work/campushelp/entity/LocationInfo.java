package com.dream.work.campushelp.entity;

import com.baidu.mapapi.model.LatLng;

import java.io.File;

/**
 * Created by Dream on 2017/4/23.
 */

public class LocationInfo {
    public File file;
    public LatLng latLng;
    public int info_id;

    public LocationInfo(File file, LatLng latLng, int uid) {
        this.file = file;
        this.latLng = latLng;
        this.info_id = uid;
    }
}

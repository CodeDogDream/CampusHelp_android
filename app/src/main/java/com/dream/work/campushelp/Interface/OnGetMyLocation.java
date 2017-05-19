package com.dream.work.campushelp.Interface;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;

/**
 * Created by Dream on 2017/5/16.
 */

public interface OnGetMyLocation {
    void onGetLocation(BDLocation bdLocation, LocationClient locationClient);
}

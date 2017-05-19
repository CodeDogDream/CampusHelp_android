package com.dream.work.campushelp.utils;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.baidu.mapapi.model.LatLng;
import com.dream.work.campushelp.CampusApplication;
import com.dream.work.campushelp.entity.UserInfo;
import com.dream.work.campushelp.entity.UserLogin;
import com.dream.work.campushelp.helper.SharePreferenceHelper;

/**
 * Created by Dream on 2017/4/3.
 */

public class RuntimeInfo {
    private static RuntimeInfo instance;
    public String token = "";
    public String uid;
    public String uname;
    public String mobile;
    public boolean isLogin;
    public String longitude;
    public String latitude;


    public static RuntimeInfo getInstance() {
        if (instance == null) {
            instance = new RuntimeInfo();
        }
        return instance;
    }

    private RuntimeInfo() {

    }

    public void saveUserInfo(UserInfo userInfo) {
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userInfo", "uname", userInfo.getUname());
        updateInfo();
    }

    public void saveLocation(LatLng latLng) {
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "longitude", String.valueOf(latLng.longitude));
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "latitude", String.valueOf(latLng.latitude));
        updateInfo();
    }

    public void saveUserLogin(UserLogin userLogin) {
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "token", userLogin.getToken());
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "uid", userLogin.getUid());
        SharePreferenceHelper.saveSharePreferenceFromBoolean(CampusApplication.getInstance().getApplicationContext(), "userLogin", "isLogin", true);
        updateInfo();
    }

    public void updateInfo() {
        token = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "token");
        uid = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "uid");
        isLogin = SharePreferenceHelper.getSharePreferenceFromBoolean(CampusApplication.getInstance().getApplicationContext(), "userLogin", "isLogin");
        uname = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userInfo", "uname");
        longitude = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "longitude");
        latitude = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "latitude");
    }

    public void clearData() {
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "token", "");
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "uid", "");
        SharePreferenceHelper.saveSharePreferenceFromBoolean(CampusApplication.getInstance().getApplicationContext(), "userLogin", "isLogin", false);
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "longitude", "");
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "LatLng", "latitude", "");
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userInfo", "uname", "");
        updateInfo();
    }
}

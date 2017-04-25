package com.dream.work.campushelp.utils;

import com.dream.work.campushelp.CampusApplication;
import com.dream.work.campushelp.entity.UserInfo;
import com.dream.work.campushelp.entity.UserLogin;
import com.dream.work.campushelp.helper.SharePreferenceHelper;

/**
 * Created by Dream on 2017/4/3.
 */

public class RuntimeInfo {
    private static RuntimeInfo instance;
    public String token;
    public String uid;
    public String mobile;

    public static RuntimeInfo getInstance() {
        if (instance == null) {
            instance = new RuntimeInfo();
        }
        return instance;
    }

    private RuntimeInfo() {

    }

    public void saveUserLogin(UserLogin userLogin) {
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "token", userLogin.getToken());
        SharePreferenceHelper.saveSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "uid", userLogin.getUid());
        updateInfo();
    }

    public void updateInfo() {
        token = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "token");
        uid = SharePreferenceHelper.getSharePreferenceFromString(CampusApplication.getInstance().getApplicationContext(), "userLogin", "uid");
    }
}

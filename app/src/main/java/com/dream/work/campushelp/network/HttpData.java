package com.dream.work.campushelp.network;

/**
 * Created by Administrator on 2017/3/14.
 */

public class HttpData {
    public static String url = "http://172.16.0.8/";

    public static void setUrl(String mUrl) {
        url = mUrl;
    }

    public static final String GET_CAPTCHA = "activity_login/getCaptcha";

    public static final String USER_LOGIN = "activity_login/userLogin";

    public static final String GET_USER_INFO = "user/getUserInfo";

    public static final String UPDATE_USER_INFO = "user/updateUserInfo";

    public static final String UPDATE_USER_LOCATION = "user/updateLocation";

    public static final String UPDATE_AVATAR = "user/updateAvatar";

    public static final String PUBLISH_HELP = "help/publishHelp";

    public static final String CHANGE_HELP_INFO = "help/changeHelpInfo";

    public static final String GET_ALL_HELP_INFO = "help / getAllHelpInfo";

    public static final String GET_TAG_HELP_INFO = "help/getTagHelpInfo";

    public static final String GET_ID_HELP_INFO = "help/getIdHelpInfo";


    public static final String GET_NEARBY_HELP_INFO = "help/getNearByHelpInfo";


    public static final String GET_HELP_INFO_BY_INFO_ID = "help/getHelpInfoByInfoid";


}

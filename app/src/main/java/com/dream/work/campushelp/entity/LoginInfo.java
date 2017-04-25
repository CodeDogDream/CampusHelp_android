package com.dream.work.campushelp.entity;

/**
 * 用户登录相关
 * Created by Dream on 2017/3/3.
 */
public class LoginInfo {
    //用户id
    private int uid;
    //用户手机
    private long mobile;
    //手机验证码
    private String captcha;
    //上次发送时间
    private long sendTime;
    //本日发送次数 可能用不到，数据不太好清空暂且保留
    private int sendCount;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "uid=" + uid +
                ", mobile=" + mobile +
                ", captcha=" + captcha +
                ", sendTime=" + sendTime +
                ", sendCount=" + sendCount +
                '}';
    }
}

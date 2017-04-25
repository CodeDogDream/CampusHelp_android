package com.dream.work.campushelp.entity;

/**
 * Created by Dream on 2017/3/3.
 */
public class UserInfo {
    //用户id
    private int uid;
    //用户昵称
    private String uname;
    //    用户手机
    private String mobile;
    //    用户个性签名
    private String uintro;
    //    用户头像地址
    private String uavatar;
    //    用户年龄
    private int age;
    //    用户性别
    private String gender;
    //    用户学校
    private String school;
    //用户的技能（标签）
    private String tag;
    //用户所在经度
    private double longitude;
    //用户所在纬度
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public UserInfo(String uname, String mobile, String uintro, String uavatar, Integer age, String gender, String school, String tag, double longitude, double latitude) {
        this.uname = uname;
        this.mobile = mobile;
        this.uintro = uintro;
        this.uavatar = uavatar;
        this.age = age;
        this.gender = gender;
        this.school = school;
        this.tag = tag;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserInfo() {

    }

    public UserInfo(int uid, String uname, String mobile, String uintro, String uavatar, Integer age, String gender, String school, String tag, double longitude, double latitude) {
        this.uid = uid;
        this.uname = uname;
        this.mobile = mobile;
        this.uintro = uintro;
        this.uavatar = uavatar;
        this.age = age;
        this.gender = gender;
        this.school = school;
        this.tag = tag;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", mobile=" + mobile +
                ", uintro='" + uintro + '\'' +
                ", uavatar='" + uavatar + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", school='" + school + '\'' +
                ", tag='" + tag +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUintro() {
        return uintro;
    }

    public void setUintro(String uintro) {
        this.uintro = uintro;
    }

    public String getUavatar() {
        return uavatar;
    }

    public void setUavatar(String uavatar) {
        this.uavatar = uavatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

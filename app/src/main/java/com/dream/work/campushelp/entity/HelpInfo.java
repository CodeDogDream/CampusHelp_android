package com.dream.work.campushelp.entity;

/**
 * Created by Dream on 2017/3/3.
 */
public class HelpInfo {
    //求助信息的编号
    private Integer infoId;
    //    用户id
    private Integer uid;
    //    用户昵称
    private String uname;
    //    求助信息标题
    private String title;
    //    求助信息内容
    private String content;

    private String uavatar;

    public String getUavatar() {
        return uavatar;
    }

    public void setUavatar(String uavatar) {
        this.uavatar = uavatar;
    }

    @Override
    public String toString() {
        return "HelpInfo{" +
                "infoId=" + infoId +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", tag='" + tag + '\'' +
                '}';
    }

    //    求助发起的时间戳
    private Long date;
    //    求助发起的经度
    private Double longitude;
    //    求助发起的纬度
    private Double latitude;
    //    求助所属的类别
    private String tag;

    public HelpInfo(Integer uid, Integer infoId, String uname, String title, String content, Long date, Double longitude, Double latitude, String tag) {
        this.uid = uid;
        this.infoId = infoId;
        this.uname = uname;
        this.title = title;
        this.content = content;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tag = tag;
    }

    public HelpInfo() {
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

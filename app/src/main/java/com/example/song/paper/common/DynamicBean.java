package com.example.song.paper.common;

import java.util.List;

public class DynamicBean {
    private String id;//动态id
    private String userid;//发布者id
    private String portrait;//头像
    private String name;//昵称
    private String time;//发布时间
    private String content;//内容
    private String pinglun_num;//评论数量
    private String dianzan_num;//点赞数量
    private List<String> images;//图片列表
    private boolean isdianzan;//是否点赞

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPinglun_num() {
        return pinglun_num;
    }

    public void setPinglun_num(String pinglun_num) {
        this.pinglun_num = pinglun_num;
    }

    public String getDianzan_num() {
        return dianzan_num;
    }

    public void setDianzan_num(String dianzan_num) {
        this.dianzan_num = dianzan_num;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isIsdianzan() {
        return isdianzan;
    }

    public void setIsdianzan(boolean isdianzan) {
        this.isdianzan = isdianzan;
    }
}

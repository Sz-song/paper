package com.example.song.paper.mine;

import java.util.List;

public class MineBean {
    private String useraccountid;//id
    private String portrait;//头像
    private String name;//昵称
    private String introduce;//简介内容
    private String fans_num;//评论数量
    private String focus_num;//点赞数量

    public String getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(String useraccountid) {
        this.useraccountid = useraccountid;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
    }

    public String getFocus_num() {
        return focus_num;
    }

    public void setFocus_num(String focus_num) {
        this.focus_num = focus_num;
    }
}

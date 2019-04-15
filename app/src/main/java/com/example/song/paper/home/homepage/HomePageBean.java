package com.example.song.paper.home.homepage;

import com.example.song.paper.common.AuctionBean;

import java.util.List;

public class HomePageBean {
    private List<AuctionBean> list;
    private List<String> bannerimages;
    private String portrait;
    private String name;
    private String txt;

    public List<AuctionBean> getList() {
        return list;
    }

    public List<String> getBannerimages() {
        return bannerimages;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getName() {
        return name;
    }

    public String getTxt() {
        return txt;
    }
}

package com.example.song.paper.common;

import java.util.List;

public class AuctionBean {
    private String id;
    private String image;//预览图
    private String name;//名称
    private String price_now;//当前价
    private String price_start;//起拍价
    private long time_start;
    private long time_end;
    private long time_now;
    private List<String> images;


    public long getTime_start() {
        return time_start;
    }

    public long getTime_end() {
        return time_end;
    }

    public long getTime_now() {
        return time_now;
    }

    public List<String> getImages() {
        return images;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice_now() {
        return price_now;
    }

    public String getPrice_start() {
        return price_start;
    }
}

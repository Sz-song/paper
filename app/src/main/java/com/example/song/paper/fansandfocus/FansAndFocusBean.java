package com.example.song.paper.fansandfocus;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:08
 */
public class FansAndFocusBean {
    private String id;
    private String name;
    private String portrait;
    private String txt;
    private int isfocus;
    private int fans_num;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPortrait() {
        return portrait;
    }

    public String getTxt() {
        return txt;
    }

    public int getIsfocus() {
        return isfocus;
    }

    public int getFans_num() {
        return fans_num;
    }

    public void setIsfocus(int isfocus) {
        this.isfocus = isfocus;
    }
}

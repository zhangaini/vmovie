package com.example.zhang.vmovie.seriesbean;

/**
 * Created by Administrator on 2017/6/19.
 */

public class DataBean {
    private String image;
    private String title;
    private String update_to;
    private String follower_num;
    private String content;
    private String seriesid;

    public String getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(String seriesid) {
        this.seriesid = seriesid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdate_to() {
        return update_to;
    }

    public void setUpdate_to(String update_to) {
        this.update_to = update_to;
    }

    public String getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(String follower_num) {
        this.follower_num = follower_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

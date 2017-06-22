package com.example.zhang.vmovie.seriesbean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class Detail_Data_Bean {
    private String title;
    private String content;
    private String weekly;
    private List<PostBean> posts;

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

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public List<PostBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostBean> posts) {
        this.posts = posts;
    }
}

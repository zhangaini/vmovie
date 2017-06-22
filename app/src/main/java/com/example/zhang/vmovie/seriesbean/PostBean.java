package com.example.zhang.vmovie.seriesbean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */

public class PostBean {
    private String from_to;
    private List<ListBaseBean> list;

    public String getFrom_to() {
        return from_to;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public List<ListBaseBean> getList() {
        return list;
    }

    public void setList(List<ListBaseBean> list) {
        this.list = list;
    }
}

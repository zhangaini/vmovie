package com.example.zhang.vmovie.JavaBean_FirstFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */

public class First_ViewPagerDataBean {
    private String image;
    private First_Viewpager_ExtraBean extra_data;

    public First_Viewpager_ExtraBean getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(First_Viewpager_ExtraBean extra_data) {
        this.extra_data = extra_data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

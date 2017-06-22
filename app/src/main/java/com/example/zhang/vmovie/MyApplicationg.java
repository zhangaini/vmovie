package com.example.zhang.vmovie;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/6/15.
 */

public class MyApplicationg extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化各种框架 此处只有xutils
        x.Ext.init(this);
        //得到打印的日志
        x.Ext.setDebug(true);
    }
}

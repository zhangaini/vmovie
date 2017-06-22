package com.example.zhang.vmovie.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/6/22.
 */

public class Serier_Detail_Activity extends BaseActivity {
    private String url;
    private TabLayout series_tab;
    private ViewPager series_viewpager;
    private boolean isExit;

    @Override
    protected int getLayoutId() {
        return R.layout.series_tab_viewpager;
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        series_tab = (TabLayout) findViewById(R.id.series_detail_tab);
        series_viewpager = (ViewPager) findViewById(R.id.series_detail_viewpager);


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(!isExit) {
                Toast.makeText(this, "再按一次推出", Toast.LENGTH_SHORT).show();
                isExit=true;
                //定时任务 再几秒内没有点击又变成false
                Timer timer=new Timer();
                TimerTask task=new TimerTask() {
                    @Override
                    public void run() {
                        isExit=false;
                    }
                };
                timer.schedule(task,3*1000);//三秒后执行task
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

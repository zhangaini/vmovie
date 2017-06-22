package com.example.zhang.vmovie.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class WelconActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mviewpager;
    private MyAdapter adapter;
    private RadioGroup mviewradiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcon);
        initView();
    }

    private void initView() {
        mviewpager = (ViewPager) findViewById(R.id.my_viewpager);
        adapter = new MyAdapter(getSupportFragmentManager(),getData());
        mviewpager.setAdapter(adapter);
        mviewpager.addOnPageChangeListener(this);
        mviewradiogroup = (RadioGroup) findViewById(R.id.viewpager_control);
        RadioButton childone= (RadioButton)mviewradiogroup.getChildAt(0);
        childone.setChecked(true);

    }

    public List<Fragment> getData() {
        List<Fragment> data= new ArrayList<Fragment>();
        data.add(new Guideone());
        data.add(new Guidetwo());
        data.add(new Guidethree());
        return data;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 第二个参数和第三个参数相乘就是偏移量 第二个[0,1）
        Log.e("onPageScrolled", "onPageScrolled: " );

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("onPageSelected", "onPageSelected: "+position);
        RadioButton child= (RadioButton)mviewradiogroup.getChildAt(position);
        child.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("onPageScroll", "onPageScrollStateChanged: "+state);

    }
}

package com.example.zhang.vmovie.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.contants.SharedParams;

public class FlashActivity extends BaseActivity implements Animation.AnimationListener {


    private ImageView mFlashBg;
    private ImageView mFlashLogo;
    private ImageView mFlashTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    protected void initView() {
        mFlashBg = (ImageView) findViewById(R.id.flash_bg);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.flash_scale);
        //给动画添加一个监听 播放完就跳转
        animation.setAnimationListener(this);
        mFlashBg.startAnimation(animation);
        mFlashLogo = (ImageView) findViewById(R.id.flash_logo);
        mFlashTitle = (ImageView) findViewById(R.id.flash_title);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //动画结束 决定动画跳转到主页还是导航页 使用数据持久化技术
        SharedPreferences sdf = getSharedPreferences(SharedParams.NAME, MODE_PRIVATE);
        boolean firstuse = sdf.getBoolean(SharedParams.FIRST_USE, true);
        if(firstuse) {
            //跳转到导航页，记录使用标识
            SharedPreferences.Editor editor=sdf.edit();
            editor.putBoolean(SharedParams.FIRST_USE,false);
            editor.apply();
            startActivity(new Intent(this,WelconActivity.class));
        }
        else {
            //跳转到主页面
            startActivity(new Intent(this,MainActivity_1.class));
        }

        finish();//防止回退到欢迎页面
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

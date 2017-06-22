package com.example.zhang.vmovie.activitys;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.fragments.FirstPageFragment;
import com.example.zhang.vmovie.fragments.SecondPageFragment;
import com.example.zhang.vmovie.fragments.ThirdPageFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity_1 extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, Animator.AnimatorListener {


    private FirstPageFragment mFirstPage;
    private SecondPageFragment mSecondPage;
    private ThirdPageFragment mThirdPage;
    private RadioGroup mController;
    private View mcover;
    private RadioButton mIndexRadio;
    private ImageView mClose;
    private RadioButton mRadioIndex;
    private RadioButton mRadioSeries;
    private RadioButton mRadioBehind;
    private ObjectAnimator scaleX;
    private ObjectAnimator scaleY;
    private boolean isExit;
    private ImageView mLogin;
    private ImageView mSet;
    private ImageView mDown;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_;
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

    @Override
    protected void initView() {
          /*
        动态加载fragment
        1获取一个fragmentmanager
        2开启一个fragmenttransaction
        3添加动作 显示或者隐藏
        3提交事务
         */
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

//添加动作
        mFirstPage = new FirstPageFragment();
        mSecondPage = new SecondPageFragment();
        mThirdPage = new ThirdPageFragment();
        transaction.add(R.id.main_container, mFirstPage).add(R.id.main_container, mSecondPage).add(R.id.main_container, mThirdPage);
//将所有的页面隐藏
        transaction.hide(mFirstPage).hide(mSecondPage).hide(mThirdPage);
        transaction.commit();

        //初始化控件
        mController = (RadioGroup) findViewById(R.id.main_cotroller);
        mController.setOnCheckedChangeListener(this);

        //初始化 覆盖的页面
        mcover = findViewById(R.id.main_cover);
        mIndexRadio = (RadioButton) findViewById(R.id.index);
        mIndexRadio.setChecked(true);

        mClose = (ImageView) findViewById(R.id.side_close);
        mClose.setOnClickListener(this);


        //将三个radiobutton找出来
        mRadioIndex = (RadioButton) findViewById(R.id.index);
        mRadioSeries = (RadioButton) findViewById(R.id.series);
        mRadioBehind = (RadioButton) findViewById(R.id.behind);
        mRadioIndex.setOnClickListener(this);
        mRadioSeries.setOnClickListener(this);
        mRadioBehind.setOnClickListener(this);
        //登录 下载 设置等
        mLogin = (ImageView) findViewById(R.id.my_touxiang);
        mSet = (ImageView) findViewById(R.id.my_touxiang_left_set);
        mDown = (ImageView) findViewById(R.id.my_download);
        mLogin.setOnClickListener(this);
        mSet.setOnClickListener(this);
        mDown.setOnClickListener(this);


    }
    public void openCover(){
        mcover.setVisibility(View.VISIBLE);
        //添加动画 关闭按钮的放大动画 控制按钮的显示动画
        scaleX = ObjectAnimator.ofFloat(mClose,"scaleX",0,0.8f,1,1.2f,1);
        scaleY = ObjectAnimator.ofFloat(mClose,"scaleY",0,0.8f,1,1.2f,1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.setDuration(2000);
        animatorSet.start();
        Animation fir = AnimationUtils.loadAnimation(this, R.anim.firstpage_in);
        Animation sec = AnimationUtils.loadAnimation(this, R.anim.second_in);
        Animation third = AnimationUtils.loadAnimation(this, R.anim.third_in);
        mRadioIndex.startAnimation(fir);
        mRadioSeries.startAnimation(sec);
        mRadioBehind.startAnimation(third);



    }
    public void closeCover(){

        mcover.setVisibility(View.GONE);

    }

    public void closeCoverAnim()
    {
        scaleX = ObjectAnimator.ofFloat(mClose,"scaleX",1,1.2f,0,0.8f,1);
        scaleY = ObjectAnimator.ofFloat(mClose,"scaleY",1,1.2f,0,0.8f,1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.setDuration(1000);
        animatorSet.addListener(this);
        animatorSet.start();
    }
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (checkedId) {
            case R.id.index:
                transaction.hide(mSecondPage).hide(mThirdPage).show(mFirstPage);

                break;
            case R.id.series:
                transaction.hide(mFirstPage).hide(mThirdPage).show(mSecondPage);

                break;
            case  R.id.behind:
                transaction.hide(mFirstPage).hide(mSecondPage).show(mThirdPage);

                break;
        }
        transaction.commit();
        closeCover();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
          case  R.id.side_close :
              closeCoverAnim();
          //  closeCover();
            break;
            case R.id.index:
            case  R.id.series:
            case  R.id.behind:
                closeCover();
                break;
            case R.id.my_touxiang_left_set:
                Intent intent=new Intent(this,SetActivity.class);
                startActivity(intent);

                break;
            case R.id.my_touxiang:
                Intent intent2=new Intent(this,LoginActivity.class);
                startActivity(intent2);

                break;
            case R.id.my_download:
                Intent intent3=new Intent(this,CacheActivity.class);
                startActivity(intent3);

                break;
        }

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        closeCover();

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}

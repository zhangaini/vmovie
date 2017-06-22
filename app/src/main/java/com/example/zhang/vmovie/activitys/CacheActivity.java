package com.example.zhang.vmovie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.R;

public class CacheActivity extends BaseActivity implements View.OnClickListener {


    private ImageView mImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cache;
    }

    @Override
    protected void initView() {
        mImageView = (ImageView) findViewById(R.id.activity_cache_back);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_cache_back:
                finish();
                break;
        }
    }
}

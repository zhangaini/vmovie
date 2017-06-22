package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/19.
 */

public class First_Fragment_1_ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<ImageView> mview;
    public First_Fragment_1_ViewPagerAdapter(Context context, ArrayList<ImageView> view) {
        this.context=context;
        this.mview=view;
    }

    @Override
    public int getCount() {
       // Log.e("TAG", "getCount: "+mview.size() );
        return mview.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mview.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mview.get(position));
        return  mview.get(position);
    }
}

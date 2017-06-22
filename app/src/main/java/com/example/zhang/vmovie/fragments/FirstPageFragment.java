package com.example.zhang.vmovie.fragments;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_IndexBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_Index_DateBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.MainActivity_1;
import com.example.zhang.vmovie.adapters.My_Index_ViewPager_Adapter;
import com.example.zhang.vmovie.adapters.My_first_Adapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class FirstPageFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String TAG ="TAG" ;
    private ImageView mOpen;
    private View mindicator;
    private TextView mZuixin;
    private TextView mPindao;
    private List<First_Index_DateBean> mydata;
    private String MURL="http://app.vmoiver.com/apiv3/post/getPostByTab";
    private String MURL_2="http://app.vmoiver.com/apiv3/cate/getList";
    private My_first_Adapter adapter;
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    @Override
    protected void initView() {
        mOpen = (ImageView) findViewById(R.id.open_cover);
        mOpen.setOnClickListener(this);
        mindicator = findViewById(R.id.my_indicator);
        mZuixin = (TextView) findViewById(R.id.my_zuixin);
        mPindao = (TextView) findViewById(R.id.my_pindao);
        mPindao.setOnClickListener(this);
        mZuixin.setOnClickListener(this);
//        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//         mRecyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new My_first_Adapter(getContext(),getData());
//        mRecyclerView.setAdapter(adapter);
//
//        loadingData();
        mViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        My_Index_ViewPager_Adapter viewPager_adapter = new My_Index_ViewPager_Adapter(getActivity().getSupportFragmentManager(), getFragData());

        mViewPager.setAdapter(viewPager_adapter);
        mViewPager.addOnPageChangeListener(this);
    }

    private List<Fragment> getFragData() {
        List<Fragment> data =new ArrayList<>();
        data.add(new FirstPageFragment_1());
        data.add(new FirstPageFragment_2());
        return data;
    }

    private List<First_Index_DateBean> getData() {
        List<First_Index_DateBean> data =new ArrayList<>();
//        for(int i=0;i<30;i++){
//            First_Index_DateBean bean=new First_Index_DateBean();
//            bean.setTitle("你曾是少年"+i);
//            data.add(bean);
//
//        }
        return data;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_cover:
//                 打开activity中的cover 菜单


                MainActivity_1 activity = (MainActivity_1) getActivity();
//                fragment必须依附于activity  这里getactivity得到的就是他依附的
                activity.openCover();

                break;
            //二个页面加载的数据不同而已用的仍然是相同的fragment，现在还没有添加手势控制 到时候
            //添加手势控制的时候来根据改变的像素来控制滚动条的缓慢移动

                case R.id.my_zuixin:
                   mViewPager.setCurrentItem(0);

                    break;
                case R.id.my_pindao:
                    mViewPager.setCurrentItem(1);

                    break;
            }



    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int width=mPindao.getWidth();


        mindicator.setTranslationX(width*(position+positionOffset));
    }

    @Override
    public void onPageSelected(int position) {
        mZuixin.setTextColor(Color.GRAY);
        mPindao.setTextColor(Color.GRAY);
        if(position==0)
            mZuixin.setTextColor(Color.WHITE);
        if(position==1)
            mPindao.setTextColor(Color.WHITE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

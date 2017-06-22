package com.example.zhang.vmovie.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.MainActivity_1;
import com.example.zhang.vmovie.adapters.My_Behind_ViewPager_Adapter;
import com.example.zhang.vmovie.behindbean.BehinCateBean;
import com.example.zhang.vmovie.behindbean.BehindDataBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ThirdPageFragment extends BaseFragment implements View.OnClickListener{
    private static final java.lang.String URL_BEHIND = "http://app.vmoiver.com/apiv3/backstage/getCate";
    private ImageView mOpen;
    private ViewPager mBehindViewPager;
    private My_Behind_ViewPager_Adapter adapter;
    private TabLayout mTab;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_thirdpage;
    }
    @Override
    protected void initView() {

            /*
        9.TabLayout：design包里 导航条，通常结合ViewPager进行使用
        -TabLayout有一个模式属性 scrollable 可以拉伸标题栏长度  fixed默认填满
        -ViewPager结合使用：TabLayout调用setupWithViewPager ViewPager的PagerAdapter实现getPageTitle方法
        -**Fragment传值 setArguments 向Fragment中传值，这个Fragment是没有加载过的
        **getArgument 获取传递过来的值
         */
        mOpen = (ImageView) findViewById(R.id.open_cover);
        mOpen.setOnClickListener(this);
        //下面是幕后的上面滑动条 和下方的内容。
        mBehindViewPager = (ViewPager) findViewById(R.id.behind_viewpager);
        adapter = new My_Behind_ViewPager_Adapter(getActivity().getSupportFragmentManager(),getListData());
        //试试那个getfragment是什么情况
        mBehindViewPager.setAdapter(adapter);

        //tablayout的使用
        //tablayout 和viewpager进行绑定 还需要重写PagerAdapter中的getPageTitle的方法
        mTab = (TabLayout) findViewById(R.id.behind_tab);
        mTab.setupWithViewPager(mBehindViewPager);
        loadingData();


    }

    private void loadingData() {
        //获取网络数据
        RequestParams paras= new RequestParams(URL_BEHIND);
        x.http().get(paras, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BehindDataBean behindDataBean=gson.fromJson(result,BehindDataBean.class);
                List<BehinCateBean> cateBean=behindDataBean.getData();
                Log.e("TAG", "onSuccess: "+cateBean.size() );
                adapter.updata(cateBean);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        
    }

    private List<Fragment> getListData() {
        List<Fragment> fragmentList =new ArrayList<>();
//        fragmentList.add(new Test_Frangment_1());
//        fragmentList.add(new Test_Frangment_2());
        return fragmentList;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_cover:
                MainActivity_1 activity = (MainActivity_1) getActivity();
                activity.openCover();
        }
    }
}

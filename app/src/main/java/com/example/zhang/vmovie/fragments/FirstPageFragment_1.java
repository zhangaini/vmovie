package com.example.zhang.vmovie.fragments;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_IndexBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_Index_DateBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_ViewPagerDataBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_ViewPagerListDataBean;
import com.example.zhang.vmovie.R;
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

public class FirstPageFragment_1 extends BaseFragment {
    private static final String TAG = "TAG";
    private List<First_Index_DateBean> mydata;
    private String MURL = "http://app.vmoiver.com/apiv3/post/getPostByTab";
    private String MURL_2 = "http://app.vmoiver.com/apiv3/cate/getList";
    private String MURL_HEAD="http://app.vmoiver.com/apiv3/index/getBanner";

    private My_first_Adapter adapter;
    private RecyclerView mRecyclerView;
    private ArrayList<View> listpagerView;


    @Override
    protected int getLayoutId() {
        return R.layout.first_fragment_1;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new My_first_Adapter(getContext(), getData());
        mRecyclerView.setAdapter(adapter);
    //    loadingChangePager();//首页轮播的添加

        loadingData();
        loadingData2();

    }

//    private void loadingChangePager() {
//
//
//
//        adapter.loadingChangerPager(data);
//
//    }


    private List<First_Index_DateBean> getData() {
        List<First_Index_DateBean> data = new ArrayList<>();
//        for(int i=0;i<30;i++){
//            First_Index_DateBean bean=new First_Index_DateBean();
//            bean.setTitle("你曾是少年"+i);
//            data.add(bean);
//
//        }
        return data;

    }


    private void loadingData2(){
        //这里先把轮播的数据获取了再说
        RequestParams mypagerparas=new RequestParams(MURL_HEAD);
        x.http().get(mypagerparas, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson_head = new Gson();
                First_ViewPagerListDataBean listBean=gson_head.fromJson(result,First_ViewPagerListDataBean.class);

                List<First_ViewPagerDataBean> viewPagerBean= listBean.getData();
              //  adapter.loadingData2(viewPagerBean);
                Log.e(TAG, "轮播数据"+viewPagerBean.get(0).getExtra_data().getApp_banner_param());

                adapter.updateSomelist(viewPagerBean);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                Log.e(TAG, "onError: "+ex );
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



    }

    private void loadingData() {
        /*
        网络请求以及更新数据
        构建一个RequestParams
        调用Http模块将请求发送出去。
         */

        //模块的获取
        RequestParams requestParams = new RequestParams(MURL);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: " + result);

                //数据解析
                Gson gson = new Gson();
                // MovieListBean movieListBean = gson.fromJson(result, MovieListBean.class);


                First_IndexBean data = gson.fromJson(result, First_IndexBean.class);
                List<First_Index_DateBean> data1 = data.getData();
                //刷新适配器
                //  adapter.upDataResource(movieListBean.getData());
                adapter.loadingData(data1);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "onFinished: ");
                //  refresh.setRefreshing(false);
            }
        });


    }


}

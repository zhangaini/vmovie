package com.example.zhang.vmovie.fragments;


import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.JavaBean_FirstFragment.FirstFragment_2_DataBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.FirstFragment_2_ListDataBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_IndexBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_Index_DateBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.adapters.My_first_Adapter_2;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class FirstPageFragment_2 extends BaseFragment {
    private static final String TAG = "TAG";
    private List<First_Index_DateBean> mydata;
    private String MURL = "http://app.vmoiver.com/apiv3/cate/getList";
    private My_first_Adapter_2 adapter;
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.first_fragment_1;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new My_first_Adapter_2(getContext(), getData());
        mRecyclerView.setAdapter(adapter);
        loadingData();

    }


    private List<FirstFragment_2_DataBean> getData() {
        List<FirstFragment_2_DataBean> data = new ArrayList<>();
//        for(int i=0;i<30;i++){
//            FirstFragment_2_DataBean bean=new FirstFragment_2_DataBean();
//            bean.setCatename("你曾是少年"+i);
//            data.add(bean);
//
//        }
        return data;

    }


    private void loadingData() {
        /*
        网络请求以及更新数据
        构建一个RequestParams
        调用Http模块将请求发送出去。
         */

        RequestParams requestParams = new RequestParams(MURL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                //数据解析
                Gson gson = new Gson();



                FirstFragment_2_ListDataBean listDataBean = gson.fromJson(result, FirstFragment_2_ListDataBean.class);
               List<FirstFragment_2_DataBean> dataBean=listDataBean.getDataBeen();
                //刷新适配器

                adapter.loadingData(dataBean);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError: "+ex.getLocalizedMessage()+ex.getMessage());
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

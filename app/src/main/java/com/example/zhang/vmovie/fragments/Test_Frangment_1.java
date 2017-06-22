package com.example.zhang.vmovie.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.adapters.Behind_Recyclerview_Adapter;
import com.example.zhang.vmovie.behindbean.BehinCateBean;
import com.example.zhang.vmovie.behindbean.BehindDetailDataBean;
import com.example.zhang.vmovie.behindbean.BehindDetailDataListBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/6/20.
 */

public class Test_Frangment_1 extends BaseFragment{

    private static final String URL_KIND = "http://app.vmoiver.com/apiv3/backstage/getPostByCate";
    private RecyclerView mBehindRecyclerView;
    private String myid;
    private Behind_Recyclerview_Adapter behind_recyclerview_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.test_item_1;
    }

    @Override
    protected void initView() {
        myid= (String) getArguments().get("data");
        mBehindRecyclerView = (RecyclerView) findViewById(R.id.behind_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mBehindRecyclerView.setLayoutManager(linearLayoutManager);
        behind_recyclerview_adapter = new Behind_Recyclerview_Adapter(getContext(),getData());
        mBehindRecyclerView.setAdapter(behind_recyclerview_adapter);
        loadData();//加载真实的数据

    }

    private List<BehindDetailDataBean> getData() {
        List<BehindDetailDataBean> data=new ArrayList<>();

        BehindDetailDataBean bean= new BehindDetailDataBean();
        bean.setTitle("瞅一眼");
        data.add(bean);
        return data;
    }


    private void loadData() {
        RequestParams params =new RequestParams(URL_KIND+"?"+"cateid="+myid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BehindDetailDataListBean behindDetailDataListBean=gson.fromJson(result,BehindDetailDataListBean.class);
                List<BehindDetailDataBean>  dataBeanList=behindDetailDataListBean.getData();
                behind_recyclerview_adapter.updata(dataBeanList);


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
}

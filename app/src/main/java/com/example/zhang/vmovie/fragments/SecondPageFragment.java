package com.example.zhang.vmovie.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.zhang.vmovie.BaseFragment;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.MainActivity_1;
import com.example.zhang.vmovie.adapters.My_Second_Series_RecyclerAdapter;
import com.example.zhang.vmovie.seriesbean.DataBean;
import com.example.zhang.vmovie.seriesbean.ListDataBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class SecondPageFragment extends BaseFragment implements View.OnClickListener {
    private static final String SERIESURL = "http://app.vmoiver.com/apiv3/series/getList";
    private ImageView mOpen;
    private RecyclerView mrecyclerView;
    private My_Second_Series_RecyclerAdapter adapter;

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_secondpage;
    }

    @Override
    protected void initView() {
        mOpen = (ImageView) findViewById(R.id.open_cover);
        mOpen.setOnClickListener(this);
        mrecyclerView = (RecyclerView) findViewById(R.id.series_recyclerview);
        LinearLayoutManager manager= new LinearLayoutManager(getActivity());
        mrecyclerView.setLayoutManager(manager);
        adapter = new My_Second_Series_RecyclerAdapter(getContext(),getData());
        mrecyclerView.setAdapter(adapter);

        loadingData();
    }

    private void loadingData() {
        RequestParams requestParams= new RequestParams(SERIESURL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ListDataBean listDataBean=gson.fromJson(result,ListDataBean.class);
                List<DataBean> dataBean=listDataBean.getData();
                adapter.updata(dataBean);


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

    private List<DataBean> getData() {
        List<DataBean> data = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            String s= "hehe"+i;
//            data.add(s);
//        }

        return data;
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

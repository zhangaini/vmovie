package com.example.zhang.vmovie.activitys;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.PindaoBean.DataBean;
import com.example.zhang.vmovie.PindaoBean.ListDataBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.adapters.My_First_2_Details_Adapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class FirstPageFragment_2_Detail_Activity extends BaseActivity {
    private static final java.lang.String DETAIL_2_URL = "http://app.vmoiver.com/apiv3/post/getPostInCate";
    private RecyclerView mRecyclerview;
    private My_First_2_Details_Adapter adapter;
    String myid;

    @Override
    protected int getLayoutId() {
        return R.layout.first_2_detail_activity;
    }

    @Override
    protected void initView() {
         mRecyclerview = (RecyclerView) findViewById(R.id.first_detail_2_recycler);
         myid=getIntent().getStringExtra("myid");//得到传递过来的id来进行加载哪个界面数据

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        adapter = new My_First_2_Details_Adapter(this,getData());
        loadDingdata();
        mRecyclerview.setAdapter(adapter);
    }

    private List<DataBean> getData() {
        List<DataBean> bean=new ArrayList<>();
        return  bean;

    }

    private void loadDingdata() {
        String newDETAIL_2_URL=DETAIL_2_URL+"?"+"cateid="+myid;
        Log.e("TAG", "lo转换地址:"+newDETAIL_2_URL );
        RequestParams params=new RequestParams(newDETAIL_2_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ListDataBean dataBeanList=gson.fromJson(result, ListDataBean.class);
                List<DataBean> dataBeen=dataBeanList.getData();
                adapter.updata(dataBeen);

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

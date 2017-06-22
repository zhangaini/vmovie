package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.Serier_Detail_Activity;
import com.example.zhang.vmovie.activitys.Test_Activity;
import com.example.zhang.vmovie.seriesbean.DataBean;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class My_Second_Series_RecyclerAdapter extends RecyclerView.Adapter<My_Second_Series_RecyclerAdapter.MySeconHolder> implements View.OnClickListener {
    private Context context;
    private List<DataBean> data;
    private RecyclerView mrecyclerview;

    public My_Second_Series_RecyclerAdapter(Context context,List<DataBean> data) {
        this.context=context;
        this.data=data;
    }

    public void updata(List<DataBean> dataBean){
        this.data.clear();
        this.data.addAll(dataBean);
        notifyDataSetChanged();

    }
    @Override
    public MySeconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.series_item,parent,false);
        return new MySeconHolder(view);

    }

    @Override
    public void onBindViewHolder(MySeconHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.info.setText("更新至"+data.get(position).getUpdate_to()+"集   "+data.get(position).getFollower_num()+"人已订阅");
        holder.detail.setText(data.get(position).getContent());
        x.image().bind(holder.image,data.get(position).getImage());
        holder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mrecyclerview=recyclerView;
    }

    @Override
    public void onClick(View v) {
//        int position=mrecyclerview.getChildAdapterPosition(v);
//        Intent intent = new Intent(v.getContext(), Serier_Detail_Activity.class);
//        String seriesid=data.get(position).getSeriesid();
//        String murl="http://app.vmoiver.com/apiv3/series/view?seriesid="+seriesid;
//        intent.putExtra("url",murl);
//        v.getContext().startActivity(intent);
        //暂时不跳转了
    }

    public static class MySeconHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,info,detail;
        public MySeconHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.series_image);
            title=(TextView) itemView.findViewById(R.id.series_title);
            info=(TextView) itemView.findViewById(R.id.series_info);
            detail=(TextView) itemView.findViewById(R.id.series_detail);
        }
    }
}

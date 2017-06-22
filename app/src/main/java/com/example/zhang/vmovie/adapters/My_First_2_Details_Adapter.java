package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.PindaoBean.DataBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.FirstPageFragment_2_Detail_Activity;
import com.example.zhang.vmovie.activitys.Test_Activity;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class My_First_2_Details_Adapter extends RecyclerView.Adapter<My_First_2_Details_Adapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private List<DataBean> data;
    private RecyclerView mRecyclerView;

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.firstfragmentitem, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.text.setText(data.get(position).getTitle());
        x.image().bind(holder.imag, data.get(position).getImage());
        int time = Integer.parseInt(data.get(position).getDuration());
        int min = time / 60;
        int sec = time % 60;
        holder.mDuration.setText(data.get(position).getCates().get(0).getCatename() + "|" + min + "'" + sec + "''");

        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updata(List<DataBean> dataBeen) {
        this.data.clear();
        this.data.addAll(dataBeen);
        notifyDataSetChanged();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public void onClick(View v) {
         int position = mRecyclerView.getChildAdapterPosition(v);
        String murl = data.get(position).getRequest_url();//将这种类型的id传递过去
        //页面跳转 将postid传递到下一个页面
        Intent intent = new Intent(v.getContext(), Test_Activity.class);
        intent.putExtra("url",murl);

        v.getContext().startActivity(intent);
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        ImageView imag;
        TextView text;
        TextView mDuration;
        public MyHolder(View itemView) {
            super(itemView);
            imag= (ImageView) itemView.findViewById(R.id.first_frag_image);
            text=(TextView)itemView.findViewById(R.id.first_frag_text);
            mDuration=(TextView)itemView.findViewById(R.id.first_frag_duration);
        }
    }
    public My_First_2_Details_Adapter(Context context,List<DataBean> data) {

        inflater=LayoutInflater.from(context);
        this.data=data;

    }




}

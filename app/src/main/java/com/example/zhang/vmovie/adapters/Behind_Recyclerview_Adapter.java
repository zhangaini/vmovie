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
import com.example.zhang.vmovie.activitys.FirstPageFragment_2_Detail_Activity;
import com.example.zhang.vmovie.activitys.Test_Activity;
import com.example.zhang.vmovie.behindbean.BehindDetailDataBean;

import org.w3c.dom.Text;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class Behind_Recyclerview_Adapter extends RecyclerView.Adapter<Behind_Recyclerview_Adapter.Behind_Holder> implements View.OnClickListener {
    private Context context;
    private  String id;
    private LayoutInflater inflater;
    private List<BehindDetailDataBean> data=new ArrayList<>();
    private RecyclerView mRecyclerView;

    public Behind_Recyclerview_Adapter(Context context,List<BehindDetailDataBean> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public Behind_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.behind_recyleritem,parent,false);
        return  new Behind_Holder(view);

    }

    @Override
    public void onBindViewHolder(Behind_Holder holder, int position) {
        x.image().bind(holder.image,data.get(position).getImage());
        holder.text.setText(data.get(position).getTitle());
        holder.share.setText(data.get(position).getShare_num());
        holder.like.setText(data.get(position).getLike_num());
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public void updata(List<BehindDetailDataBean> dataBeanList) {
        data.clear();
        data.addAll(dataBeanList);
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


    public static class Behind_Holder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text,share,like;

        public Behind_Holder(View itemView) {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.behind_recyclervieitem_image);
            text=(TextView)itemView.findViewById(R.id.behind_recyclervieitem_text);
            share=(TextView)itemView.findViewById(R.id.behinditem_share);
            like=(TextView)itemView.findViewById(R.id.behinditem_like);
        }
    }
}

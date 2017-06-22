package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.JavaBean_FirstFragment.FirstFragment_2_DataBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.FirstFragment_2_ListDataBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_Index_DateBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.FirstPageFragment_2_Detail_Activity;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class My_first_Adapter_2 extends RecyclerView.Adapter<My_first_Adapter_2.Myholder> implements View.OnClickListener {

    private final LayoutInflater inflater;
    private List<FirstFragment_2_DataBean> data;
    private Context context;
    private ImageOptions imageOptions;
    private RecyclerView mRecyclerView;

    public My_first_Adapter_2(Context context, List<FirstFragment_2_DataBean> data) {

        this.data=data;
        this.context=context;
        inflater= LayoutInflater.from(context);
        imageOptions= new ImageOptions.Builder()
                //加载过程
                .setLoadingDrawableId(R.mipmap.loading)

                //加载失败显示
                .setFailureDrawableId(R.mipmap.error)
                .setFadeIn(true)
                .setCircular(true)

                .build();

//        //直接加载数据
//        this.data.clear();
//        this.data.addAll(data);
//        notifyDataSetChanged();
    }

    public void loadingData(List<FirstFragment_2_DataBean> data){
       // Log.e("TAG", "loadingData: "+data.get(3).getCatename() );
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();

    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.firstfragmentitem_2,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        x.image().bind(holder.imag,data.get(position).getIcon());
        holder.text.setText("# "+data.get(position).getCatename()+" #");

        //添加监听
        holder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }


    //private RecyclerView mRecyclerView; 上面已经添加
    //当itemView 贴附在recyclerview上的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }

    @Override
    public void onClick(View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);
        String cateid = data.get(position).getCateid();//将这种类型的id传递过去
        //页面跳转 将postid传递到下一个页面
        Intent intent = new Intent(v.getContext(), FirstPageFragment_2_Detail_Activity.class);
        intent.putExtra("myid",cateid);

        v.getContext().startActivity(intent);
    }

    public static class Myholder extends RecyclerView.ViewHolder {

        ImageView imag;
        TextView text;

        public Myholder(View itemView) {
            super(itemView);
            imag= (ImageView) itemView.findViewById(R.id.item_2_image);
            text=(TextView)itemView.findViewById(R.id.item_2_text);

        }
    }
}

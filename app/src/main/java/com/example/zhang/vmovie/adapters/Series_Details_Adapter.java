package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhang.vmovie.R;

/**
 * Created by Administrator on 2017/6/21.
 */

public class Series_Details_Adapter extends RecyclerView.Adapter<Series_Details_Adapter.MySeriesHolder> {

    private LayoutInflater inflater;
    public Series_Details_Adapter(Context context) {
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MySeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=inflater.inflate(R.layout.serier_details_item,parent,false);
        return new MySeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(MySeriesHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MySeriesHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView text;
        public MySeriesHolder(View itemView) {
            super(itemView);
            image= ((ImageView) itemView.findViewById(R.id.series_detail_image));
            text= ((TextView) itemView.findViewById(R.id.series_detail_text));
        }
    }
}

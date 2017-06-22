package com.example.zhang.vmovie.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhang.vmovie.JavaBean_FirstFragment.First_Index_DateBean;
import com.example.zhang.vmovie.JavaBean_FirstFragment.First_ViewPagerDataBean;
import com.example.zhang.vmovie.R;
import com.example.zhang.vmovie.activitys.FirstPageFragment_2_Detail_Activity;
import com.example.zhang.vmovie.activitys.Test_Activity;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class My_first_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ViewPager.OnPageChangeListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "TAG";

    private  LayoutInflater inflater;
    private List<First_Index_DateBean> data;
    private Context context;
    private ImageOptions imageOptions;
    private List<First_ViewPagerDataBean> myviewPagerBean;
    private ArrayList<ImageView> somelist;
    private First_Fragment_1_ViewPagerAdapter mFirstFragment1ViewPagerAdapter;
    private RecyclerView mRecyclerView;
    private int temp=0;
    private int i;
    private int pagePosition;


    public My_first_Adapter(Context context,List<First_Index_DateBean> data) {

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




   public void updateSomelist(List<First_ViewPagerDataBean> viewPagerBean){

       myviewPagerBean=viewPagerBean;
       //将图片资源的数据传递过来-


    }

    public void loadingData(List<First_Index_DateBean> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 0) {
            View view = inflater.inflate(R.layout.headviewholder, parent, false);
            return new HeadViewHolder(view);
        }
        else {
            View view = inflater.inflate(R.layout.firstfragmentitem, parent, false);
            return new Myholder(view);
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof  HeadViewHolder){
            final HeadViewHolder headholder= ((HeadViewHolder) holder);
            //添加悬浮在图片上的button
            headholder.radiogroup.removeAllViews();

            for(i=0;i<myviewPagerBean.size();i++){

                RadioButton button=new RadioButton(context);

                button.setButtonDrawable(R.drawable.radio);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        headholder.pager.setCurrentItem(headholder.radiogroup.getCheckedRadioButtonId()-1);
                    }
                });
                headholder.radiogroup.addView(button);



            }
            ((RadioButton) headholder.radiogroup.getChildAt(0)).setChecked(true);
            headholder.radiogroup.setOnCheckedChangeListener(this);


            mFirstFragment1ViewPagerAdapter = new First_Fragment_1_ViewPagerAdapter(context,getViewPagerAdapter());
            headholder.pager.setAdapter(mFirstFragment1ViewPagerAdapter);
            headholder.pager.addOnPageChangeListener(this);



        }
        else{
            Myholder myholder= ((Myholder) holder);
            myholder.text.setText(data.get(position-1).getTitle());
            x.image().bind(myholder.imag, data.get(position-1).getImage());
            int time = Integer.parseInt(data.get(position-1).getDuration());
            int min = time / 60;
            int sec = time % 60;
            myholder.mDuration.setText(data.get(position-1).getCates().get(0).getCatename() + "|" + min + "'" + sec + "''");
        }
        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Test_Activity.class);
                String murl=data.get(position-1).getRequest_url();
                intent.putExtra("url",murl);
                v.getContext().startActivity(intent);


            }
        });




    }



    private ArrayList<ImageView> getViewPagerAdapter() {
       ArrayList<ImageView> somelist = new ArrayList<>();

        for(int i=0;i<myviewPagerBean.size();i++){


            //添加viewpager中的图片
            ImageView view=new ImageView(context);

            view.setOnClickListener(this);

            x.image().bind(view,myviewPagerBean.get(i).getImage());
            somelist.add(view);
        }

        return  somelist;
    }



    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPagePosition(position);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setPagePosition(int pagePosition) {
        this.pagePosition = pagePosition;
    }

    public int getPagePosition() {
        return pagePosition;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(v.getContext(), Test_Activity.class);
        String murl=myviewPagerBean.get(getPagePosition()).getExtra_data().getApp_banner_param();
        intent.putExtra("url",murl);
        v.getContext().startActivity(intent);

        Toast.makeText(context, "当前位置"+getPagePosition(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {

        }
    }


    public static class HeadViewHolder extends  RecyclerView.ViewHolder implements ViewPager.OnPageChangeListener {


        ViewPager pager;
        RadioGroup radiogroup;
        public HeadViewHolder(View itemView) {

            super(itemView);

            radiogroup=(RadioGroup)itemView.findViewById(R.id.first_radiogroup);
            pager = ((ViewPager) itemView.findViewById(R.id.my_first_viewpager));
            pager.addOnPageChangeListener(this);



        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            ((RadioButton) radiogroup.getChildAt(position)).setChecked(true);
//           View view= pager.getChildAt(position);
//            view.setOnClickListener(this);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }



    }
    public static class Myholder extends RecyclerView.ViewHolder {

        ImageView imag;
        TextView text;
        TextView mDuration;

       // ViewPager pager;
        public Myholder(View itemView) {

            super(itemView);
            imag= (ImageView) itemView.findViewById(R.id.first_frag_image);
            text=(TextView)itemView.findViewById(R.id.first_frag_text);
            mDuration=(TextView)itemView.findViewById(R.id.first_frag_duration);

          //  pager = ((ViewPager) itemView.findViewById(R.id.first_viewpager));
        }
    }



}

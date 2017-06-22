package com.example.zhang.vmovie.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhang.vmovie.behindbean.BehinCateBean;
import com.example.zhang.vmovie.fragments.Test_Frangment_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class My_Behind_ViewPager_Adapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<BehinCateBean> mycateBean=new ArrayList<>();
    private Test_Frangment_1 tempfragment;

    public My_Behind_ViewPager_Adapter(FragmentManager fm,List<Fragment> fragmentList_) {
        super(fm);
        this.fragmentList=fragmentList_;

    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      if (mycateBean==null){
          return "?";
      }
      else
          return mycateBean.get(position).getCatename();
      //  return "幕后";
    }

    public void updata(List<BehinCateBean> cateBean) {
        //这里来更新fragment的数量
        //mycateBean.clear();
        fragmentList.clear();
        //mycateBean=cateBean;
        mycateBean.addAll(cateBean);  //这里哈为什么addAll会是空的？//额因为没有初始化- -赋值会直接初始化。
        for(int i=0;i<mycateBean.size();i++){
            tempfragment = new Test_Frangment_1();
            Bundle budle=new Bundle();
            budle.putString("data",mycateBean.get(i).getCateid());
            tempfragment.setArguments(budle);
            fragmentList.add(tempfragment);
        }
        notifyDataSetChanged();
    }
}

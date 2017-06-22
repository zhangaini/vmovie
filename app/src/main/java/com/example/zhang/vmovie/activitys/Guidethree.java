package com.example.zhang.vmovie.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zhang.vmovie.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class Guidethree extends Fragment implements View.OnClickListener {
    private View mylayout;
    private ImageView mgo_second;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mylayout = inflater.inflate(R.layout.fragmentguidethree,container,false);
        mgo_second = (ImageView) findViewById(R.id.go_second);
        mgo_second.setOnClickListener(this);
        return mylayout;
    }
    private View findViewById(int id){
        return mylayout.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.go_second:
                Intent intent = new Intent(getActivity(), MainActivity_1.class);
                getActivity().startActivity(intent);
                getActivity().finish();

                break;
        }
    }
}

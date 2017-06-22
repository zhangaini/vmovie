package com.example.zhang.vmovie.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhang.vmovie.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class Guideone extends Fragment {
    private View mylayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mylayout = inflater.inflate(R.layout.fragmentguideone,container,false);
        return mylayout;
    }
}

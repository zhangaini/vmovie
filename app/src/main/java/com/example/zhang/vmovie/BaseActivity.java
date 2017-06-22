package com.example.zhang.vmovie;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
* 项目开发流程
*   1 梳理需求
*   2确认需求
*   3为项目需求做准备 知识储备 有很多是没接触过的（学习）得到提升
*   4技能储备 同一种需求 第二次做的时候应该有提升
*   5技术选型 开发框架
*   6创建基础工程
*       -新项目 添加好所需的各种依赖
*       -封装常用工具类 经常用到的比如获取屏幕高度等
*       -创建自己的基类 ---activity的控制（第一行代码上有讲解）
*   7开发 使用静态数据填充页面
*   8网络请求填充真实数据
*
* */

public abstract class BaseActivity extends AppCompatActivity {
    //作为一个基类

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
}

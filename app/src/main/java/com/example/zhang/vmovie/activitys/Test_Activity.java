package com.example.zhang.vmovie.activitys;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.zhang.vmovie.BaseActivity;
import com.example.zhang.vmovie.R;

/**
 * Created by Administrator on 2017/6/21.
 */

public class Test_Activity extends BaseActivity {

    private  WebView mWebView;
    @Override
    protected int getLayoutId() {
        return R.layout.test_item_2;
    }

    @Override
    protected void initView() {
        String murl=getIntent().getStringExtra("url");
        if(!murl.contains("vmoiver"))//有时候传递过来的是id而不是一个网站链接所有不是链接的时候
        {
            murl="http://app.vmoiver.com/"+murl+"?qingapp=app_new";
        }
       // Toast.makeText(this, "values->"+murl, Toast.LENGTH_SHORT).show();
        mWebView = (WebView) findViewById(R.id.item_2_web);
        //需要打开支持javascript功能
        mWebView.getSettings().setJavaScriptEnabled(true);
        //为webview设置自己的客户端
        mWebView.setWebViewClient(new WebViewClient());
        //加载进度 以及js调用监控的客户端
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl(murl);

    }
}

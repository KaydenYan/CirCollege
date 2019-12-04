package com.example.funnews;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class NewsInfoActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initViews();
    }

    /**
     * 初始化数据
     */

    public void initViews(){

        webView = (WebView)findViewById(R.id.wv_content);

        /**
         * 获得传递过来的数据
         */
        Intent intent = this.getIntent();

        String url = intent.getStringExtra("url");

        webView.loadUrl(url);

    }



}

package com.example.funnews;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.funnews.josnGet.NewsInfoActivity_db;
import com.example.funnews.josnGet.toutiao;

import java.util.ArrayList;

public class NewsInfoActivity extends AppCompatActivity {


    private com.tencent.smtt.sdk.WebView webView;
    private ImageView likeBtn;
    private CustomeOnClickListener listener;
    private NewsInfoActivity_db dbHelper;
    private SQLiteDatabase db;
    private ArrayList<toutiao> toutiaos1;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initViews();
        getViews();
        registerListeners();
        initDBbyDatabaseHelper();
        toutiaos1=new ArrayList<toutiao>();

    }

    private void registerListeners() {

        listener =new CustomeOnClickListener();
        likeBtn.setOnClickListener(listener);
    }

    private void getViews() {
        likeBtn =findViewById(R.id.likeBtn);
        webView = findViewById(R.id.wv_content);
    }

    /**
     * 初始化数据
     */

    public void initViews(){
        webView =findViewById(R.id.wv_content);
        /**
         * 获得传递过来的数据
         */
        Intent intent = this.getIntent();

        String url = intent.getStringExtra("url");
        webView.loadUrl(url);

    }

    private void insertdb(){
        for(int i=0;i<30;i++){
            ContentValues cv =new ContentValues();

            String title = getIntent().getStringExtra("title");
            cv.put("title",title);
            String date =getIntent().getStringExtra("date");
            cv.put("date",date);
            String author_name =getIntent().getStringExtra("author_name");
            cv.put("author_name",author_name);
            String url =getIntent().getStringExtra("url");
            cv.put("url",url);
            String thumbnail_pic_s =getIntent().getStringExtra("thumbnail_pic_s");
            if(thumbnail_pic_s !=null){
                cv.put("thumbnail_pic_s",thumbnail_pic_s);
            }else {
                cv.put("thumbnail_pic_s",(String)null);
            }

            String thumbnail_pic_s02 =getIntent().getStringExtra("thumbnail_pic_s02");
            if(thumbnail_pic_s02 !=null){
                cv.put("thumbnail_pic_s02",thumbnail_pic_s02);
            }else{
                cv.put("thumbnail_pic_s02",(String)null);
            }

            String thumbnail_pic_s03 =getIntent().getStringExtra("thumbnail_pic_s03");
            if(thumbnail_pic_s03!=null){
                cv.put("thumbnail_pic_s03",thumbnail_pic_s03);
            }else{
                cv.put("thumbnail_pic_s03",(String)null);
            }

            Toast.makeText(
                    getApplicationContext(),
                    "收藏成功",
                    Toast.LENGTH_SHORT
            ).show();
            db.replace("likenews",null,cv);
        }
    }
    private void initDBbyDatabaseHelper() {
        dbHelper =new NewsInfoActivity_db(this,"likenew",null,1);
        db =dbHelper.getWritableDatabase();
    }

    class CustomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.likeBtn:
                    insertdb();
                    break;
            }
        }
    }

}

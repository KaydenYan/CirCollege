package com.example.funnews;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funnews.josnGet.NewsInfoActivity_db;

import java.util.ArrayList;

public class likenews_view extends AppCompatActivity {
    private com.tencent.smtt.sdk.WebView webView;
    private likenews_view.CustomeOnClickListener listener;
    private NewsInfoActivity_db dbHelper;
    private SQLiteDatabase db;
    private ArrayList<likenews> likenews;
    private int position;
    private String[][] d;
    private ImageView delete_item;
    private ImageView commBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.like_activity_news_info);
        initViews();
        getViews();
        registerListeners();
        initDBbyDatabaseHelper();

        final int[] flag = {0};
        /*likeBtn.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(flag[0]){
                    case 0:
                        insertdb();
                        likeBtn.setColorFilter(getResources().getColor(R.color.colorTheme));
                        shoucang();
                        flag[0] =1;
                        break;
                    case 1:
                        delete_db();
                        flag[0]=0;
                        break;
                }
            }
        });*/
    }

    private void registerListeners() {
        listener =new likenews_view.CustomeOnClickListener();
        delete_item.setOnClickListener(listener);
        commBtn.setOnClickListener(listener);
    }


    private void getViews() {
        webView = findViewById(R.id.wv_content);
        delete_item =findViewById(R.id.delete_item);
        commBtn =findViewById(R.id.commBtn);
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
            cv.put("thumbnail_pic_s",thumbnail_pic_s);


            String thumbnail_pic_s02 =getIntent().getStringExtra("thumbnail_pic_s02");
            if(thumbnail_pic_s02 =="null"){
                cv.put("thumbnail_pic_s02","null");
            }else{
                cv.put("thumbnail_pic_s02",thumbnail_pic_s02);
            }

            String thumbnail_pic_s03 =getIntent().getStringExtra("thumbnail_pic_s03");
            if(thumbnail_pic_s03=="null"){
                cv.put("thumbnail_pic_s03","null");
            }else{
                cv.put("thumbnail_pic_s03",thumbnail_pic_s03);
            }
            String shoucang =getIntent().getStringExtra("shoucang");
            cv.put("shoucang",shoucang);
            Toast.makeText(
                    getApplicationContext(),
                    "收藏成功",
                    Toast.LENGTH_SHORT
            ).show();
            db.replace("likenews",null,cv);
            String[] args = {String.valueOf("ok")};
            String where = "shoucang=?";
            db = dbHelper.getWritableDatabase();
            db.update("likenews",cv,where,args);
        }
    }

    private void delete_db(){
        dbHelper =new NewsInfoActivity_db(this,"likenew",null,1);
        String where = "title=?";

        String str = getIntent().getStringExtra("title");
        String[] whereParam = new String[]{str};
        db = dbHelper.getWritableDatabase();
        Toast.makeText(
                getApplicationContext(),
                "删除成功",
                Toast.LENGTH_SHORT
        ).show();
        db.delete("likenews",where,whereParam);
    }


    private void initDBbyDatabaseHelper() {
        dbHelper =new NewsInfoActivity_db(this,"likenew",null,1);
        db =dbHelper.getWritableDatabase();
    }

    private void update(){
        dbHelper =new NewsInfoActivity_db(this,"likenew",null,1);
        String where = "shoucang=?";
        ContentValues cv =new ContentValues();
        String shoucang =getIntent().getStringExtra("shoucang");
        cv.put("shoucang",shoucang);
        String[] args = {String.valueOf("ok")};
        db = dbHelper.getWritableDatabase();
        db.update("likenews",cv,where,args);
        Toast.makeText(
                getApplicationContext(),
                "更新成功",
                Toast.LENGTH_SHORT
        ).show();

    }
    private ArrayList<likenews> initData() {
        //遍历Cursor


        Cursor cursor1 =db.rawQuery("select title,shoucang from likenews limit 0,10",null);
        cursor1.moveToFirst();
        int i=0;
        while (!cursor1.isAfterLast()) {
            likenews d=new likenews();
            d.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
            d.setShoucang(cursor1.getString(cursor1.getColumnIndex("shoucang")));
            i=i+1;

            cursor1.moveToNext();
        }

        return likenews;
    }

    class CustomeOnClickListener implements View.OnClickListener {

        private Object no;

        @Override
        public void onClick(View v) {
            initData();
            ArrayList<likenews> tt=new ArrayList<>();
            switch (v.getId()){
                case R.id.delete_item:
                    delete_db();
                    delete_item.setColorFilter(getResources().getColor(R.color.black));
                    break;
                case R.id.commBtn:
                    Intent intent1 = new Intent(likenews_view.this,
                            pinglun.class);
                    startActivity(intent1);
                    break;
            }
        }

    }

}

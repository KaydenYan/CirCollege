package com.example.funnews;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.funnews.josnGet.NewsInfoActivity_db;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class NewsInfoActivity extends AppCompatActivity {


    private com.tencent.smtt.sdk.WebView webView;
    private ImageView likeBtn;
    private CustomeOnClickListener listener;
    private NewsInfoActivity_db dbHelper;
    private SQLiteDatabase db;
    private ArrayList<likenews> likenews;
    private int position;
    private String[][] d;
    private ImageView delete_item;
    protected boolean useThemestatusBarColor = false;//false状态栏透明，true状态栏使用颜色
    protected boolean useStatusBarColor = true;//false状态栏图标浅色，true状态栏颜色深色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        setStatusBar();
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
        listener =new CustomeOnClickListener();
        likeBtn.setOnClickListener(listener);
        delete_item.setOnClickListener(listener);
    }


    private void getViews() {
        likeBtn =findViewById(R.id.likeBtn);
        webView = findViewById(R.id.wv_content);
        delete_item =findViewById(R.id.delete_item);
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
    protected void setStatusBar() {//状态栏沉浸，状态栏颜色，状态栏系统图标的深浅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTheme));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            //Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
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
                case R.id.likeBtn:
                    ContentValues cv =new ContentValues();
                    cv.getAsString("title");
                    String title = getIntent().getStringExtra("title");
                    if(tt.size()==0){
                        insertdb();
                        likeBtn.setColorFilter(getResources().getColor(R.color.colorTheme));
                    }else{
                        if(title.equals(tt.get(0).getTitle())){
                            insertdb();
                            likeBtn.setColorFilter(getResources().getColor(R.color.colorTheme));
                        }else{
                            delete_db();
                        }
                    }
                    break;
                case R.id.delete_item:
                    delete_db();
                    break;
            }
        }

    }
    public void shoucang(){
        ContentValues cv =new ContentValues();
        if(cv.getAsString("shoucang")=="ok"){
            likeBtn.setColorFilter(getResources().getColor(R.color.colorTheme));
        }
    }
}

package com.example.funnews;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funnews.josnGet.NewsInfoActivity_db;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class sss extends AppCompatActivity {
    private ArrayList<likenews> likenews ;
    private ListView newsList;
    private like_MyAdapter adapter;
    private SmartRefreshLayout srl;
    private CustomeOnClickListener listener;
    private ImageView homepage;
    private ImageView biopage;
    private SQLiteDatabase db;
    protected boolean useThemestatusBarColor = false;//false状态栏透明，true状态栏使用颜色
    protected boolean useStatusBarColor = true;//false状态栏图标浅色，true状态栏颜色深色
    private NewsInfoActivity_db dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.likey_list);
        super.onCreate(savedInstanceState);
        homepage=findViewById(R.id.homeBtn);
        biopage=findViewById(R.id.myBtn);
        setStatusBar();
        registListener();
        likenews=new ArrayList<likenews>();
        com.tencent.smtt.sdk.WebView webView = new com.tencent.smtt.sdk.WebView(this);

        int width = webView.getView().getWidth();

        //初始化数据
        newsList =findViewById(R.id.likeyList);

        adapter = new like_MyAdapter(this,likenews);
        run();
    }
    private void initDBbyDatabaseHelper() {

        dbHelper = new NewsInfoActivity_db(this,"likenew",null,1);
        db =dbHelper.getWritableDatabase();
    }
    private void loadMoreData() {
        List<likenews> tt=new ArrayList<>();
        Cursor cursor1 =db.rawQuery("select author_name,title,date,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03,shoucang from likenews",null);
        cursor1.moveToFirst();

        while (!cursor1.isAfterLast()) {
            likenews d=new likenews();

            d.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
            d.setAuthor_name(cursor1.getString(cursor1.getColumnIndex("author_name")));
            d.setDate(cursor1.getString(cursor1.getColumnIndex("date")));
            d.setUrl(cursor1.getString(cursor1.getColumnIndex("url")));
            d.setThumbnail_pic_s(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s")));
            d.setThumbnail_pic_s02(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s02")));
            d.setThumbnail_pic_s03(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s03")));
            d.setShoucang(cursor1.getString(cursor1.getColumnIndex("shoucang")));
            likenews.add(d);
            cursor1.moveToNext();
        }

        adapter.notifyDataSetChanged();
    }
    private void registListener(){
        listener = new CustomeOnClickListener();
        homepage.setOnClickListener(listener);
        biopage.setOnClickListener(listener);
    }
    protected void setStatusBar() {//状态栏沉浸，状态栏颜色，状态栏系统图标的深浅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (false) {
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && true) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    class CustomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.homeBtn:
                    Intent intent2 = new Intent(
                            sss.this,
                            MainActivity.class
                    );
                    startActivity(intent2);
                    break;
                case R.id.myBtn:
                    Intent intent3 = new Intent(
                            sss.this,
                            loginActivity.class
                    );
                    startActivity(intent3);
                    break;
            }
        }
    }
    private void refreshData() {
        likenews.clear();
        likenews.addAll(initData());
        adapter.notifyDataSetChanged();
    }

    private ArrayList<likenews> initData() {
        //遍历Cursor

        ArrayList<likenews> tt=new ArrayList<>();
        Cursor cursor1 =db.rawQuery("select author_name,title,date,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03, shoucang from likenews",null);
        cursor1.moveToFirst();
        int i=0;
        while (!cursor1.isAfterLast()) {
            likenews d=new likenews();
            d.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
            d.setAuthor_name(cursor1.getString(cursor1.getColumnIndex("author_name")));
            d.setDate(cursor1.getString(cursor1.getColumnIndex("date")));
            d.setUrl(cursor1.getString(cursor1.getColumnIndex("url")));
            d.setThumbnail_pic_s(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s")));
            d.setThumbnail_pic_s02(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s02")));
            d.setThumbnail_pic_s03(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s03")));
            d.setShoucang(cursor1.getString(cursor1.getColumnIndex("shoucang")));
            i=i+1;
            Log.e("TAGxx ----------", i + cursor1.getString(cursor1.getColumnIndex("author_name")));
            likenews.add(d);
            cursor1.moveToNext();
        }

        return likenews;
    }

    public void run() {
        //List<toutiao> toutiaos =new ArrayList<>();
        initDBbyDatabaseHelper();
        newsList.setAdapter(adapter);
        final ArrayList<likenews> finalToutiaos = likenews;
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        finalToutiaos.get(position).getAuthor_name(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        likenews = initData();
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                        /**
                         * 创建一个意图
                         */
                        Intent intent = new Intent(sss.this,NewsInfoActivity.class);
                        /**
                         * 在datas中通过点击的位置position通过get()方法获得具体某个新闻
                         * 的数据然后通过Intent的putExtra()传递到NewsInfoActivity中
                         */
                        intent.putExtra("url", likenews.get(position).getUrl());
                        intent.putExtra("title",likenews.get(position).getTitle());
                        intent.putExtra("author_name",likenews.get(position).getAuthor_name());
                        intent.putExtra("date",likenews.get(position).getDate());
                        intent.putExtra("thumbnail_pic_s",likenews.get(position).getThumbnail_pic_s());
                        intent.putExtra("thumbnail_pic_s02",likenews.get(position).getThumbnail_pic_s02());
                        intent.putExtra("thumbnail_pic_s03",likenews.get(position).getThumbnail_pic_s03());
                        intent.putExtra("shoucang",likenews.get(position).getShoucang());
                        sss.this.startActivity(intent);//启动Activity
                    }
                });

            }
}

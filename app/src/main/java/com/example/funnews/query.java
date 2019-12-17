package com.example.funnews;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funnews.josnGet.DBHelper_toutiao;
import com.example.funnews.josnGet.toutiao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

public class query extends AppCompatActivity {
    private ArrayList<toutiao> toutiaos ;
    private ListView newsList;
    private MyAdapter adapter;
    private SmartRefreshLayout srl;
    private SQLiteDatabase db;
    private DBHelper_toutiao dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);

        toutiaos=new ArrayList<toutiao>();



        //初始化数据
        newsList =findViewById(R.id.likeyList);

        adapter = new MyAdapter(this,toutiaos);
        run();
    }
    private void initDBbyDatabaseHelper() {

        dbHelper = new DBHelper_toutiao(this,"news",null,1);
        db =dbHelper.getWritableDatabase();
    }
    private ArrayList<toutiao> initData() {
        //遍历Cursor
        Intent intent = this.getIntent();

        String querry = intent.getStringExtra("querry");


        Cursor cursor1 =db.rawQuery("select author_name,title,date,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03, shoucang from countinfo where title like ?",new String[]{"%"+querry+"%"});
        cursor1.moveToFirst();
        int i=0;
        while (!cursor1.isAfterLast()) {
            toutiao d=new toutiao();
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
            toutiaos.add(d);
            cursor1.moveToNext();
        }
        Toast.makeText(
                getApplicationContext(),
                "搜索成功",
                Toast.LENGTH_SHORT
        ).show();
        return toutiaos;
    }

    public void run() {
        //List<toutiao> toutiaos =new ArrayList<>();
        initDBbyDatabaseHelper();
        newsList.setAdapter(adapter);
        final ArrayList<toutiao> finalToutiaos = toutiaos;
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
        toutiaos = initData();
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                /**
                 * 创建一个意图
                 */
                Intent intent = new Intent(query.this,NewsInfoActivity.class);
                /**
                 * 在datas中通过点击的位置position通过get()方法获得具体某个新闻
                 * 的数据然后通过Intent的putExtra()传递到NewsInfoActivity中
                 */
                intent.putExtra("url", toutiaos.get(position).getUrl());
                query.this.startActivity(intent);//启动Activity
            }
        });

    }
}

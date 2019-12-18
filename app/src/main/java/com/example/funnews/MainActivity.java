package com.example.funnews;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.funnews.josnGet.DBHelper_toutiao;
import com.example.funnews.josnGet.toutiao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    //master

    private ArrayList<toutiao> toutiaos ;
    private SearchView searchView;
    private ListView newsList;
    private MyAdapter adapter;
    private TextView top;
    private TextView shehui;
    private TextView guonei;
    private TextView guoji;
    private TextView yule;
    private TextView tiyu;
    private TextView junshi;
    private TextView keji;
    private TextView caijing;
    private TextView shishang;
    private SmartRefreshLayout srl;
    private SQLiteDatabase db;
    private DBHelper_toutiao dbHelper;
    private ImageView likeBtn;
    private CustomeOnClickListener listener;
    private ImageView buttomQuery;
    private EditText editText;
    protected boolean useThemestatusBarColor = false;//false状态栏透明，true状态栏使用颜色
    protected boolean useStatusBarColor = true;//false状态栏图标浅色，true状态栏颜色深色
    private String abc;
    private ImageView mybio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new MyAdapter(this,toutiaos);
        setStatusBar();
        getView();
        com.tencent.smtt.sdk.WebView webView = new com.tencent.smtt.sdk.WebView(this);
        registListener();
        toutiaos=new ArrayList<toutiao>();
        int width = webView.getView().getWidth();
        abc="http://v.juhe.cn/toutiao/index?dtype=&type=top&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
        sendRequestWithOkHttp(abc);
        newsList =findViewById(R.id.newsList);
        adapter = new MyAdapter(this,toutiaos);}


    public void getView(){
        mybio = findViewById(R.id.myBtn);
        top=findViewById(R.id.top);
        shehui=findViewById(R.id.shehui);
        guonei=findViewById(R.id.guonei);
        guoji=findViewById(R.id.guoji);
        yule=findViewById(R.id.yule);
        tiyu=findViewById(R.id.tiyu);
        likeBtn =findViewById(R.id.likeBtn);
        editText =findViewById(R.id.edtQuery);
        buttomQuery =findViewById(R.id.buttomQuery);
        junshi=findViewById(R.id.junshi);
        keji=findViewById(R.id.keji);
        caijing=findViewById(R.id.caijing);
        shishang=findViewById(R.id.shishang);
    }
    private void registListener(){
        listener = new CustomeOnClickListener();
        mybio.setOnClickListener(listener);
        top.setOnClickListener(listener);
        shehui.setOnClickListener(listener);
        guonei.setOnClickListener(listener);
        guoji.setOnClickListener(listener);
        yule.setOnClickListener(listener);
        tiyu.setOnClickListener(listener);
        junshi.setOnClickListener(listener);
        keji.setOnClickListener(listener);
        caijing.setOnClickListener(listener);
        shishang.setOnClickListener(listener);
        likeBtn.setOnClickListener(listener);
        editText.setOnClickListener(listener);
        buttomQuery.setOnClickListener(listener);
    }
    private void settext(){
        top.setTextColor(getResources().getColor(R.color.black));
        shehui.setTextColor(getResources().getColor(R.color.black));
        guonei.setTextColor(getResources().getColor(R.color.black));
        guoji.setTextColor(getResources().getColor(R.color.black));
        yule.setTextColor(getResources().getColor(R.color.black));
        tiyu.setTextColor(getResources().getColor(R.color.black));
        junshi.setTextColor(getResources().getColor(R.color.black));
        keji.setTextColor(getResources().getColor(R.color.black));
        caijing.setTextColor(getResources().getColor(R.color.black));
        shishang.setTextColor(getResources().getColor(R.color.black));
    }
    class CustomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.myBtn:
                    Intent intent2 = new Intent(
                            MainActivity.this,
                            loginActivity.class
                    );
                    startActivity(intent2);
                    break;
                case R.id.top:
                    settext();
                    top.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=top&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.shehui:
                    settext();
                    shehui.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=shehui&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.guonei:
                    settext();
                    guonei.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=guonei&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.guoji:
                    settext();
                    guoji.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=guoji&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.yule:
                    settext();
                    yule.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=yule&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.tiyu:
                    settext();
                    tiyu.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=tiyu&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.junshi:
                    settext();
                    junshi.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=junshi&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.keji:
                    settext();
                    keji.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=keji&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.caijing:
                    settext();
                    caijing.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=caijing&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.shishang:
                    settext();
                    shishang.setTextColor(getResources().getColor(R.color.colorTheme));
                    abc="http://v.juhe.cn/toutiao/index?dtype=&type=shishang&key=fc7421a2343b5b6da2a0c3d93b571b0c&";
                    sendRequestWithOkHttp(abc);
                    break;
                case R.id.likeBtn:
                    Intent intent =new Intent();
                    intent.setClass(
                            MainActivity.this,sss.class
                    );
                    startActivity(intent);
                    break;
                case R.id.buttomQuery:
                    editText = findViewById(R.id.edtQuery);
                    String querry = editText.getText().toString();
                    Intent intent1 = new Intent(MainActivity.this,
                            query.class);
                    intent1.putExtra("querry",querry);
                    startActivity(intent1);
                    break;
            }
        }
    }

    private void loadMoreData() {
        List<toutiao> tt=new ArrayList<>();
        Cursor cursor1 =db.rawQuery("select author_name,title,date,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03,shoucang from countinfo limit 10,20",null);
        cursor1.moveToFirst();

        while (!cursor1.isAfterLast()) {
            toutiao d=new toutiao();
            d.setAuthor_name(cursor1.getString(cursor1.getColumnIndex("author_name")));
            d.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
            d.setDate(cursor1.getString(cursor1.getColumnIndex("date")));
            d.setUrl(cursor1.getString(cursor1.getColumnIndex("url")));
            d.setThumbnail_pic_s(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s")));
            d.setThumbnail_pic_s02(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s02")));
            d.setThumbnail_pic_s03(cursor1.getString(cursor1.getColumnIndex("thumbnail_pic_s03")));
            d.setShoucang(cursor1.getString(cursor1.getColumnIndex("shoucang")));
            toutiaos.add(d);
            cursor1.moveToNext();
        }

        adapter.notifyDataSetChanged();
    }

    private void refreshData() {
        toutiaos.clear();
        toutiaos.addAll(initData());
        adapter.notifyDataSetChanged();
    }

    private ArrayList<toutiao> initData() {
        //遍历Cursor

        ArrayList<toutiao> tt=new ArrayList<>();
        Cursor cursor1 =db.rawQuery("select author_name,title,date,url,thumbnail_pic_s,thumbnail_pic_s02,thumbnail_pic_s03,shoucang from countinfo limit 0,10",null);
        cursor1.moveToFirst();
        int i=0;
        while (!cursor1.isAfterLast()) {
            toutiao d=new toutiao();
            d.setAuthor_name(cursor1.getString(cursor1.getColumnIndex("author_name")));
            d.setTitle(cursor1.getString(cursor1.getColumnIndex("title")));
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

        return toutiaos;
    }



    private void convertArrayToList (String js) throws JSONException{
        JSONObject demoJson =new JSONObject(js);
        JSONObject result =demoJson.getJSONObject("result");
        JSONArray list = result.getJSONArray("data");

        for (int i =0;i<list.length();i++){
            ContentValues cv =new ContentValues();

            String uniquekey =list.getJSONObject(i).getString("uniquekey");
            cv.put("uniquekey",uniquekey);
            String title = list.getJSONObject(i).getString("title");
            cv.put("title",title);

            String date = list.getJSONObject(i).getString("date");
            cv.put("date",date);

            String category = list.getJSONObject(i).getString("category");
            cv.put("category",category);


            String author_name =list.getJSONObject(i).getString("author_name");
            cv.put("author_name",author_name);

            String url = list.getJSONObject(i).getString("url");
            cv.put("url",url);

            String thumbnail_pic_s = list.getJSONObject(i).getString("thumbnail_pic_s");
            cv.put("thumbnail_pic_s",thumbnail_pic_s);


            if(list.getJSONObject(i).has("thumbnail_pic_s02")){
                String thumbnail_pic_s02 = list.getJSONObject(i).getString("thumbnail_pic_s02");
                cv.put("thumbnail_pic_s02",thumbnail_pic_s02);
            }else{
                cv.put("thumbnail_pic_s02", "null");
            }

            if(list.getJSONObject(i).has("thumbnail_pic_s03")){
                String thumbnail_pic_s03 = list.getJSONObject(i).getString("thumbnail_pic_s03");
                cv.put("thumbnail_pic_s03",thumbnail_pic_s03);
            }else{
                cv.put("thumbnail_pic_s03", "null");
            }
            cv.put("shoucang","no");
            db.insert("countinfo",null,cv);

        }

    }

    private void initDBbyDatabaseHelper() {
        dbHelper =new DBHelper_toutiao(this,"news",null,1);
        db =dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM countinfo");
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


    private void showResponse(final String js) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //List<toutiao> toutiaos =new ArrayList<>();
                initDBbyDatabaseHelper();
                try {
                    convertArrayToList(js);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

                srl = findViewById(R.id.srl);
//        srl.setRefreshHeader(new FunGameBattleCityHeader(this));
//        srl.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
                srl.setReboundDuration(500);
                srl.setEnableScrollContentWhenLoaded(true);
                srl.autoRefresh();


                srl.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        refreshData();
                        srl.finishRefresh();

                    }
                });
                final List<toutiao> finalToutiaos1 = toutiaos;
                srl.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                        if(finalToutiaos1.size() >= 30){
                            srl.finishLoadMoreWithNoMoreData();

                        }else {
                            loadMoreData();
                            srl.finishLoadMore();

                        }
                    }
                });
                newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                            long arg3) {

                        /**
                         * 创建一个意图
                         */
                        Intent intent = new Intent(MainActivity.this,NewsInfoActivity.class);

                        /**
                         * 在datas中通过点击的位置position通过get()方法获得具体某个新闻
                         * 的数据然后通过Intent的putExtra()传递到NewsInfoActivity中
                         */

                        intent.putExtra("url", toutiaos.get(position).getUrl());
                        intent.putExtra("title",toutiaos.get(position).getTitle());
                        intent.putExtra("author_name",toutiaos.get(position).getAuthor_name());
                        intent.putExtra("date",toutiaos.get(position).getDate());
                        intent.putExtra("thumbnail_pic_s",toutiaos.get(position).getThumbnail_pic_s());
                        intent.putExtra("thumbnail_pic_s02",toutiaos.get(position).getThumbnail_pic_s02());
                        intent.putExtra("thumbnail_pic_s03",toutiaos.get(position).getThumbnail_pic_s03());
                        intent.putExtra("shoucang",toutiaos.get(position).getShoucang());
                        MainActivity.this.startActivity(intent);//启动Activity


                    }
                });



            }
        });

    }


    private void sendRequestWithOkHttp(final String ab) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建一个OkHttpClient的实例
                    OkHttpClient client = new OkHttpClient();
                    // 如果要发送一条HTTP请求，就需要创建一个Request对象
                    // 可在最终的build()方法之前连缀很多其他方法来丰富这个Request对象
                    Request request = new Request.Builder()
                            .url(ab)
                            .build();
                    // 调用OkHttpClient的newCall()方法来创建一个Call对象，并调用execute()方法来发送请求并获取服务器的返回数据
                    Response response = client.newCall(request).execute();
                    // 其中Response对象就是服务器返回的数据，将数据转换成字符串
                    String responseData = response.body().string();
                    // 将获取到的字符串传入showResponse()方法中进行UI显示
                    showResponse(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}


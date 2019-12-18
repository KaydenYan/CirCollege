package com.example.funnews;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class weather extends AppCompatActivity {

    //master


    protected boolean useThemestatusBarColor = true;//false状态栏透明，true状态栏使用颜色
    protected boolean useStatusBarColor = true;//false状态栏图标浅色，true状态栏颜色深色
    private TextView city;
    private TextView tem;
    private TextView wea;
    private TextView wind;
    private TextView windji;
    private TextView api;
    private String abc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_service);
        city=findViewById(R.id.city);
        tem=findViewById(R.id.tem);
        wea=findViewById(R.id.wea);
        wind=findViewById(R.id.wind);
        windji=findViewById(R.id.windji);
        api=findViewById(R.id.api);
        setStatusBar();
        abc="http://apis.juhe.cn/simpleWeather/query?city=石家庄&key=75993fd1fe1979ea8183643690884b00";
        sendRequestWithOkHttp(abc);
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
        }).start();}

    private void showResponse(final String js) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(js);
                    JSONObject a1=jsonObject.getJSONObject("result");
                    String aa=a1.getString("city");
                    JSONObject a2=a1.getJSONObject("realtime");
                    String te=a2.getString("temperature");
                    tem.setText(te);
                    String rr=a2.getString("info");
                    wea.setText(rr);
                    String wi=a2.getString("direct");
                    wind.setText(wi);
                    String wj=a2.getString("power");
                    windji.setText(wj);
                    String aq=a2.getString("aqi");
                    api.setText(aq);
                    city.setText(aa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }



        protected void setStatusBar() {//状态栏沉浸，状态栏颜色，状态栏系统图标的深浅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.wea));//设置状态栏背景色
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



}


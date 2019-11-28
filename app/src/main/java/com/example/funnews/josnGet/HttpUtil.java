package com.example.funnews.josnGet;

import com.example.funnews.MyApplication;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil{

    public static void sendRequestWithOkhttp(String address,okhttp3.Callback callback)
    {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
    public static void parseJsonWithJsonObject(Response response) throws IOException {
        String responseData=response.body().string();
        json_insert j=new json_insert();
        j.initDBbyDatabaseHelper(MyApplication.getGlobalContext());
        j.convertArrayToList(responseData);
    }
}

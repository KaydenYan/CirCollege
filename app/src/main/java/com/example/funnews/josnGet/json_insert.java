package com.example.funnews.josnGet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class json_insert {

    private static SQLiteDatabase db;
    //定义数据库管理类的对象
    private static Gson gson;
    private DBHelper_toutiao dbHelper;


    public void initDBbyDatabaseHelper(Context context) {
        dbHelper =new DBHelper_toutiao(context);
        db =dbHelper.getWritableDatabase();
    }


    //往数据库中写入数据
    public void convertArrayToList(String json) {

        //得到集合对应的具体类型
        Type type = new TypeToken<List<toutiao>>(){}
                .getType();
        //反序列化 添加数据
        List<toutiao> ti = gson.fromJson(json, type);

        for(int i=0;i< ti.size();i++){
            ContentValues cv =new ContentValues();
            String uniquekey = ti.get(i).getUniquekey();
            cv.put("uniquekey",uniquekey);
            String title = ti.get(i).getTitle();
            cv.put("title",title);
            String date = ti.get(i).getDate();
            cv.put("date",date);
            String category = ti.get(i).getCategory();
            cv.put("category",category);
            String author_name =ti.get(i).getAuthor_name();
            cv.put("author_name",author_name);
            String url = ti.get(i).getUrl();
            cv.put("url",url);
            String thumbnail_pic_s = ti.get(i).getThumbnail_pic_s();
            cv.put("thumbnail_pic_s",thumbnail_pic_s);
            String thumbnail_pic_s02 = ti.get(i).getThumbnail_pic_s02();
            cv.put("thumbnail_pic_s02",thumbnail_pic_s02);
            String thumbnail_pic_s03 = ti.get(i).getThumbnail_pic_s03();
            cv.put("thumbnail_pic_s03",thumbnail_pic_s03);

            long id =db.insert("countinfo",null,cv);
        }
    }
    //从数据库中调出数据
    public void toutiao_query() {

        Cursor cursor =db.query("countinfo",null,null,null,null,null,null);
        //遍历Cursor
        StringBuffer stringBuffer =new StringBuffer();
        if(cursor.moveToFirst()){
            do {
                String cUnique =cursor.getString(
                        cursor.getColumnIndex("uniquekey")
                );
                String cTitle =cursor.getString(
                        cursor.getColumnIndex("title")
                );
                String cDate =cursor.getString(
                        cursor.getColumnIndex("date")
                );
                String cCategory =cursor.getString(
                        cursor.getColumnIndex("category")
                );
                String cAuthor_name =cursor.getString(
                        cursor.getColumnIndex("author_name")
                );
                String cUrl =cursor.getString(
                        cursor.getColumnIndex("url")
                );
                String cThumbnail_pic_s =cursor.getString(
                        cursor.getColumnIndex("thumbnail_pic_s")
                );
                String cThumbnail_pic_s02 =cursor.getString(
                        cursor.getColumnIndex("thumbnail_pic_s02")
                );
                String cThumbnail_pic_s03 =cursor.getString(
                        cursor.getColumnIndex("thumbnail_pic_s03")
                );
                stringBuffer.append(cUnique+"\n"+cTitle+"\n"+cDate+"\n"+cCategory+"\n"+cAuthor_name+"\n"+cUrl+"\n"+cThumbnail_pic_s+"\n"+cThumbnail_pic_s02+"\n"+cThumbnail_pic_s03);
            }while(cursor.moveToNext());
        }
    }


}
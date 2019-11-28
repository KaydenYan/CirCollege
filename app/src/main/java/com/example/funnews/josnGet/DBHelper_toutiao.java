package com.example.funnews.josnGet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.funnews.josnGet.json_insert;

public class DBHelper_toutiao extends SQLiteOpenHelper {
    public Context context;
    public DBHelper_toutiao(Context context){
        super(context,"news.db",null,1);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table countinfo(uniquekey varchar(100) primary key , title varchar(100), date varchar(20), category varcahr(8), author_name varchar(10), url varchar(100), thumbnail_pic_s varchar(10), thumbnail_pic_s02 varchar(10), thumbnail_pic_s03 varchar(10))";
        db.execSQL(sql);
        Toast.makeText(
                context,
                "创建数据库表成功",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void onOpenDatabase(SQLiteDatabase db){
        super.onOpen(db);

    }
}

package com.example.funnews.josnGet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DBHelper_toutiao extends SQLiteOpenHelper {
    public final Context context;
    public DBHelper_toutiao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context =context;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table countinfo(uniquekey varchar(100) primary key , title varchar(100), date varchar(20), category varcahr(8), author_name varchar(10), url varchar(100), thumbnail_pic_s varchar(100), thumbnail_pic_s02 varchar(100), thumbnail_pic_s03 varchar(100), shoucang varchar(5))";
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

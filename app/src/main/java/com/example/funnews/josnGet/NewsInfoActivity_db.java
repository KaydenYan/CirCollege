package com.example.funnews.josnGet;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewsInfoActivity_db extends SQLiteOpenHelper {
    private final Context context;

    public NewsInfoActivity_db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table likenews(title varchar(100) primary key, date varchar(20),  author_name varchar(10), url varchar(100), thumbnail_pic_s varchar(100),thumbnail_pic_s02 varchar(100),thumbnail_pic_s03 varchar(100),shoucang varchar(10))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void onOpenDatabase(SQLiteDatabase db){
        super.onOpen(db);

    }
}

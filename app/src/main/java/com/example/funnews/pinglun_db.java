package com.example.funnews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class pinglun_db extends SQLiteOpenHelper {
    private final Context context;

    public pinglun_db(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context =context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table pinglunss(author_name varchar(100),pinglun varchar(1000))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void onOpenDatabase(SQLiteDatabase db){
        super.onOpen(db);

    }

}

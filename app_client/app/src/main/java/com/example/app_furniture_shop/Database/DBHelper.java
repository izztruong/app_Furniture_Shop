package com.example.app_furniture_shop.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public  static final String DB_NAME="APP_Furniture_shop";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableGioHang="create table GioHang( id integer primary key ,name text,price flaot,image text,soLuong integer)";
        db.execSQL(createTableGioHang);
        String createTableUser="create table User( id integer primary key,token text,email text)";
        db.execSQL(createTableUser);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists GioHang");
        db.execSQL("drop table if exists User");
        onCreate(db);
    }
}

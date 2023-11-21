package com.example.app_furniture_shop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.app_furniture_shop.Database.DBHelper;
import com.example.app_furniture_shop.Model.ReqLogin;

import java.util.ArrayList;

public class UserDAO {
    DBHelper dbHelper;
    public UserDAO(Context context){
        dbHelper=new DBHelper(context);
    }
    public boolean addUser(ReqLogin.Data data){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",data.getId());
        values.put("token",data.getToken());
        values.put("email",data.getEmail());
        long check=db.insert("User",null,values);
        return (check>0);

    }
    public ReqLogin.Data getUser(){
        ReqLogin.Data data=new ReqLogin.Data();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from User",null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            data.setId(cursor.getInt(0));
            data.setToken(cursor.getString(1));
            data.setEmail(cursor.getString(2));
            cursor.close();
        }
        return data;
    }
    public boolean deleteUser(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long check=db.delete("User",null,null);
        if (check==-1) {
            return false;
        }
        return true;
    }
}

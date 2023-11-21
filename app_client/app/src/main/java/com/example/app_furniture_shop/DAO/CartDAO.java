package com.example.app_furniture_shop.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_furniture_shop.Database.DBHelper;
import com.example.app_furniture_shop.Model.Cart;

import java.util.ArrayList;

public class CartDAO {
    DBHelper dbHelper;
    public CartDAO(Context context){
        dbHelper=new DBHelper(context);
    }

    public ArrayList<Cart> getAll(){
        ArrayList<Cart> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from GioHang",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new Cart(cursor.getInt(0),cursor.getString(1),cursor.getFloat(2),cursor.getString(3),cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean updateSoLuong(int id,int soLuong){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("soLuong",soLuong);
        long check=sqLiteDatabase.update("GioHang",values,"id=?",new String[]{String.valueOf(id)});
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean addCart(Cart gioHang){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",gioHang.getId());
        values.put("name",gioHang.getName());
        values.put("price",gioHang.getPrice());
        values.put("image",gioHang.getImage());
        values.put("soLuong",gioHang.getQuantity());
        long check=db.insert("GioHang",null,values);
        return (check>0);

    }
    public boolean deleteHoaDon(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long check=db.delete("GioHang","id=?",new String[]{String.valueOf(id)});
        if (check==-1)
            return false;
        return true;
    }
    public boolean delete(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        long check=db.delete("GioHang",null,null);
        if (check==-1) {
            return false;
        }
        return true;
    }

}

package com.example.paint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartManager extends SQLiteOpenHelper {
    public CartManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cart(cid INTEGER PRIMARY KEY AUTOINCREMENT, pid Integer,qty Integer, uid Integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists cart;");
        onCreate(db);+ 
    }

    public boolean addToCart( int pid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("insert into cart(pid,uid, qty) values("+pid+",1, 1)");
        return true;
    }

    public Cursor getPaints(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from cart join paint on cart.pid = paint.pid where uid = " + 1, null);
        return c;
    }

    public int Total(int uid){
        SQLiteDatabase db = this.getWritableDatabase();
        int total=0;
        Cursor c = db.rawQuery("select sum(price), sum(qty) from cart where uid = " + uid, null);
        int price = 0, qty = 0;
        while (c.moveToNext()){
            price = c.getInt(1);
            qty = c.getInt(2);
        }
        total = price * qty;
        return total;
    }


}

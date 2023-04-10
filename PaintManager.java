package com.example.paint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaintManager extends SQLiteOpenHelper {

    public PaintManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table paint(pid INTEGER PRIMARY KEY AUTOINCREMENT, pname TEXT,price INTEGER, descirption TEXT, image TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPaint(String name, int price, String desc, String img){
        SQLiteDatabase db  = this.getWritableDatabase();
        db.execSQL("insert into paint(pname, price, descirption, image) values('"+name+"', "+price+", '"+desc+"', '"+img+"');");
        return true;
    }

    public boolean updatePaint(String name, int price, String desc, String img, int pid){
        SQLiteDatabase db  = this.getWritableDatabase();
        db.execSQL("update paint set pname="+name+", price="+price+", descirption="+desc+", image="+img+" where pid = " + pid);
        return true;
    }
    public Cursor getPaints(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from paint;", null);
        return c;
    }

    public ArrayList getIDs(int uid){
        ArrayList al = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select pid from cart where uid = "+ uid,null);
        while (c.moveToNext()){
            al.add(c.getInt(0));
        }
        return al;
    }
}

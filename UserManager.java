package com.example.paint;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileWriter;
import java.io.IOException;

public class UserManager extends SQLiteOpenHelper {

    public UserManager(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("create table user(id INTEGER PRIMARY KEY AUTOINCREMENT, fname TEXT,email TEXT, password TEXT);");
        db.execSQL("CREATE TABLE paint(pid INTEGER PRIMARY KEY AUTOINCREMENT, pname TEXT,price INTEGER, descirption TEXT, image TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user;");
        db.execSQL("drop table if exists paint;");
        onCreate(db);
    }

    public int login(String email, String pass){
        int uid=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where email = ? and password = ?", new String[]{email, pass});
        if(c.getCount()!=0){
            c.moveToNext();
            uid = c.getInt(0);
        }
        return uid;
    }

    public void insert_tbl(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE paint(pid INTEGER PRIMARY KEY AUTOINCREMENT, pname TEXT,price INTEGER, descirption TEXT, image TEXT)");
        db.execSQL("INSERT INTO paint VALUES(1,'Blue',5000,'blue color','2131165409')," +
                "(2,'Black',5000,'black color','2131165408')," +
                "(3,'green',5000,'color','2131165410')," +
                "(4,'grey',5000,'color','2131165411');");
    }
    public boolean reg(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname", name);
        cv.put("email", email);
        cv.put("password", password);
        long i = db.insert("user", null, cv);
        if(i!=-1){
            return true;
        } else {
        return false; }
    }

}

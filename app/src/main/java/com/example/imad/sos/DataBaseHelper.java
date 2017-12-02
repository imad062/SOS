package com.example.imad.sos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "sostable.db";
    public static final String tableName = "sostable";
    public static final String nameColum = "name";
    public static final String phoneColum = "phonenumber";

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, 1);
        this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName +" (name text primary key,phonenum text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        onCreate(db);
    }
}

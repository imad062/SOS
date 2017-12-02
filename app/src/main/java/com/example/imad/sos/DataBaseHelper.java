package com.example.imad.sos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "sostable.db";
    public static final String tableName = "sostable";
    public static final String id = "id";
    public static final String nameColum = "name";
    public static final String phoneColum = "phonenumber";

    public DataBaseHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, " + "PHONENUMBER TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tableName);
    }

    public boolean insertData(String name, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nameColum, name);
        contentValues.put(phoneColum, phone);

        long result = db.insert(tableName, null, contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + tableName, null);
        return data;
    }
}

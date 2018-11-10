package com.bagush.vageeshcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "internalDB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(_ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, PASSWORD TEXT NOT NULL, AGE TEXT NOT NULL, WEIGHT TEXT NOT NULL, HEIGHT TEXT NOT NULL, BLOOD_GROUP TEXT NOT NULL, EMAIL TEXT NOT NULL, GENDER TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }
}

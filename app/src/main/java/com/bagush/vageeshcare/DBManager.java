package com.bagush.vageeshcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

class DBManager {

    Context context;
    SQLiteDatabase database;
    DBHelper dbHelper;

    DBManager(Context c){
        context = c;
    }

    void open(){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    void close(){
        dbHelper.close();
    }

    int insertUser(String name, String pwd, String age, String height, String weight, String gender, String blood, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("PASSWORD", pwd);
        contentValues.put("AGE", age);
        contentValues.put("WEIGHT", weight);
        contentValues.put("HEIGHT", height);
        contentValues.put("BLOOD_GROUP", blood);
        contentValues.put("EMAIL", email);
        contentValues.put("GENDER", gender);

        long res = database.insert("USERS", null, contentValues);

        return (int) res;
    }

    String searchUser(int id){
        String sql= "SELECT NAME FROM USERS WHERE _ID="+id;
        Cursor myCursor;

        myCursor = database.rawQuery(sql, null);

        if(myCursor != null){
            myCursor.moveToFirst();
            return myCursor.getString(myCursor.getColumnIndex("NAME"));
        } else {
            return  null;
        }
    }


    ArrayList<String> is_valid_login(String name, String pwd){
        String sql= "SELECT * FROM USERS WHERE NAME='"+name+"' AND PASSWORD='"+pwd+"'";
        Cursor myCursor;

        myCursor = database.rawQuery(sql, null);

        if(myCursor != null && myCursor.getCount() > 0){
            myCursor.moveToFirst();
            ArrayList<String> res = new ArrayList<String>();
            res.add(myCursor.getString(myCursor.getColumnIndex("NAME")));
            res.add(myCursor.getString(myCursor.getColumnIndex("AGE")));
            res.add(myCursor.getString(myCursor.getColumnIndex("BLOOD_GROUP")));
            res.add(myCursor.getString(myCursor.getColumnIndex("HEIGHT")));
            res.add(myCursor.getString(myCursor.getColumnIndex("WEIGHT")));
            res.add(myCursor.getString(myCursor.getColumnIndex("GENDER")));
            res.add(myCursor.getString(myCursor.getColumnIndex("EMAIL")));
            return res;
        } else {
            return  null;
        }
    }


    ArrayList<String> get_user(String name){
        String sql= "SELECT * FROM USERS WHERE NAME='"+name+"'";
        Cursor myCursor;

        myCursor = database.rawQuery(sql, null);

        if(myCursor != null && myCursor.getCount() > 0){
            myCursor.moveToFirst();
            ArrayList<String> res = new ArrayList<>();
            res.add(myCursor.getString(myCursor.getColumnIndex("NAME")));
            res.add(myCursor.getString(myCursor.getColumnIndex("AGE")));
            res.add(myCursor.getString(myCursor.getColumnIndex("BLOOD_GROUP")));
            res.add(myCursor.getString(myCursor.getColumnIndex("HEIGHT")));
            res.add(myCursor.getString(myCursor.getColumnIndex("WEIGHT")));
            res.add(myCursor.getString(myCursor.getColumnIndex("GENDER")));
            res.add(myCursor.getString(myCursor.getColumnIndex("EMAIL")));
            return res;
        } else {
            return  null;
        }
    }


}

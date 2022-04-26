package com.example.library;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class DB {

    //Select запрос к базе в виде объекта Cursor
    public static Cursor getDataFromBD(String sqlQuery, Context context){
        DatabaseHelper databaseHelper;
        SQLiteDatabase bd;
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        Cursor cursor;
        cursor = bd.rawQuery(sqlQuery,null);
        return cursor;
    }
}

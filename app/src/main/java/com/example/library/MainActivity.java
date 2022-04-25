package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lib777","start");
        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter;
        Log.d("lib777","formCreate");
        databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.updateDataBase();
            Log.d("lib777","db update");
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        Log.d("lib777", "bd create");
        Log.d("lib777", bd.getPath());
        String sql = "SELECT * FROM users";
        Cursor cursor = bd.rawQuery(sql,null);
        Log.d("lib777", "cursor ready");
        String data;
        ArrayList<String> arrayList = new ArrayList<>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Log.d("lib777", "loop in");
            data = cursor.getString(0) + " | "
                    + cursor.getString(1) + " | "
                    + cursor.getString(2) + " | "
                    + cursor.getString(3) + " | "
                    + cursor.getString(4);
            arrayList.add(data);
            Log.d("lib777",data);
            cursor.moveToNext();
        }
        cursor.close();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.layout_item,arrayList);

        listView.setAdapter(arrayAdapter);


    }
}
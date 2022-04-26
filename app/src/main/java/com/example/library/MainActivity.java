package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter;
       String sql = "SELECT * FROM users";
       Cursor cursor = DB.getDataFromBD(sql, this);
        String data;
        ArrayList<String> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            data = cursor.getString(0) + " | "
                    + cursor.getString(1) + " | "
                    + cursor.getString(2) + " | "
                    + cursor.getString(3) + " | "
                    + cursor.getString(4);
            arrayList.add(data);

            cursor.moveToNext();
        }
        cursor.close();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.layout_item,arrayList);

        listView.setAdapter(arrayAdapter);
    }


}
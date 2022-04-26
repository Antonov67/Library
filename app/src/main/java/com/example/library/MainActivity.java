package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterForMainActivity.ItemClickListener {
    ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sql = "SELECT * FROM books";
        Cursor cursor = DB.getDataFromBD(sql, this);
        bookList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            boolean wish = false;
            if (cursor.getString(6).equals("yes")){
                wish = true;
            }
            Book book = new Book(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    cursor.getString(5),
                    wish);
            bookList.add(book);
            cursor.moveToNext();
        }
        cursor.close();
      //  Log.d("lib777", bookList.toString());

       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       AdapterForMainActivity adapter = new AdapterForMainActivity(bookList,this);
       recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
       adapter.setClickListener(this);
       recyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        intent.putExtra("book", bookList.get(position));
        startActivity(intent);
    }
}
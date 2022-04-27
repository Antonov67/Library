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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterForMainActivity.ItemClickListener {
    ArrayList<Book> bookList;
    EditText searchField;
    //список для хранения полного списка книг, нужен для поиска с пустым запросом
    static ArrayList<Book> totalList = new ArrayList<>();

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


       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       AdapterForMainActivity adapter = new AdapterForMainActivity(bookList,this);
       recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
       adapter.setClickListener(this);
       recyclerView.setAdapter(adapter);

        //сохраним данные для возврата полного набора данных при необходимости
        totalList.addAll(bookList);

        searchField = findViewById(R.id.searchField);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //при вводе символов в поле ввода, применям фильтр
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<Book> books = queryBookList(charSequence);
                adapter.updateAdapter(books);
                recyclerView.getAdapter().notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        intent.putExtra("book", bookList.get(position));
        startActivity(intent);
    }

    //если искомые символы содержатся в авторе, названии или аннотации, то делаем фильтр, создаем
    // новый список книг, если же запрос пустой, то выводим полный список из переменной totalList
    public ArrayList<Book> queryBookList(CharSequence charSequence){
        Log.d("lib777","in " +  bookList.toString());
        Log.d("lib777", "chars " +  charSequence.toString());
        ArrayList<Book> books = new ArrayList<>();
        if (charSequence.length() != 0) {
            for (Book book : bookList) {
                if (book.getAuthor().contains(charSequence.toString())
                        || book.getTitle().contains(charSequence.toString())
                        || book.getAnnotation().contains(charSequence.toString())) {
                    books.add(book);
                }
            }
        }else {

            books.addAll(totalList);
        }
        return books;


    }
}
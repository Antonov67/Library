package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {
    EditText title,author, annotation, pages, year;
    Button addButton;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        addButton = findViewById(R.id.addButton);
        title = findViewById(R.id.titleAddBook);
        author = findViewById(R.id.authorAddBook);
        annotation = findViewById(R.id.annotationAddBook);
        pages = findViewById(R.id.pagesAddBook);
        year = findViewById(R.id.yearAddBook);
        context = this;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book(0,
                        title.getText().toString(),
                        author.getText().toString(),
                        annotation.getText().toString(),
                        Integer.parseInt(pages.getText().toString()),
                        year.getText().toString(),
                        false);
                DB.addBook(book,context);
                Toast.makeText(getApplicationContext(),"книга добавлена",Toast.LENGTH_LONG).show();
            }
        });
    }
}
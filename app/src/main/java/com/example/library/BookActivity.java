package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    TextView id, title, author, pages, year, annotation;
    CheckBox wish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        id = findViewById(R.id.idBook);
        title = findViewById(R.id.titleBook);
        author = findViewById(R.id.authorBook);
        annotation = findViewById(R.id.annotationBook);
        pages = findViewById(R.id.pagesBook);
        year = findViewById(R.id.yearBook);
        wish = findViewById(R.id.wishBook);

        Book book = (Book) getIntent().getSerializableExtra("book");
        id.setText(book.getId() + "");
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        annotation.setText(book.getAnnotation());
        pages.setText(book.getPageCount() + "");
        year.setText(book.getYearOfPubl());
        if (book.isWish()){
            wish.setChecked(true);
        }else {
            wish.setChecked(false);
        }

    }
}
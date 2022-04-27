package com.example.library;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String annotation;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setYearOfPubl(String yearOfPubl) {
        this.yearOfPubl = yearOfPubl;
    }

    public void setWish(boolean wish) {
        this.wish = wish;
    }

    private int pageCount;
    private String yearOfPubl;
    private boolean wish;

    public Book(int id, String title, String author, String annotation, int pageCount, String yearOfPubl, boolean wish) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.annotation = annotation;
        this.pageCount = pageCount;
        this.yearOfPubl = yearOfPubl;
        this.wish = wish;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", annotation='" + annotation + '\'' +
                ", pageCount=" + pageCount +
                ", yearOfPubl='" + yearOfPubl + '\'' +
                ", wish=" + wish +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAnnotation() {
        return annotation;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getYearOfPubl() {
        return yearOfPubl;
    }

    public boolean isWish() {
        return wish;
    }
}

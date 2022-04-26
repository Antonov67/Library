package com.example.library;

public class Book {
    private int id;
    private String title;
    private String author;
    private String annotation;
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

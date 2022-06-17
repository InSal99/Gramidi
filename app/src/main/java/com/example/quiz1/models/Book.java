package com.example.quiz1.models;

public class Book {
    private int id;
    private String name;
    private String desc;
    private String writter;
    private String hal;
    private String category;
    private String publisher;
    private String isbn;
    private String publishDate;
    private String img;

    public Book(int id, String name, String desc, String writter, String hal, String category, String publisher, String isbn, String publishDate, String img) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.writter = writter;
        this.hal = hal;
        this.category = category;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHal() {
        return hal;
    }

    public void setHal(String hal) {
        this.hal = hal;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

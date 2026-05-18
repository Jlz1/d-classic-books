package com.example.dclassicbooks.models;

public class Book {
    private String title;
    private String author;
    private String description;
    private int imageResource;
    private String category; // "Fiction" atau "Non-Fiction" untuk mempermudah filter Tab Control nanti

    // Constructor
    public Book(String title, String author, String description, int imageResource, String category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imageResource = imageResource;
        this.category = category;
    }

    // Getter methods
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
    public String getCategory() { return category; }
}
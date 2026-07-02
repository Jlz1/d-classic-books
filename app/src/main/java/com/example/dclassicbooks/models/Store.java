package com.example.dclassicbooks.models;

public class Store {
    private String name;
    private String description;
    private int imageResource;

    // Constructor
    public Store(String name, String description, int imageResource) {
        this.name = name;
        this.description = description;
        this.imageResource = imageResource;
    }

    // Getter methods
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getImageResource() { return imageResource; }
}

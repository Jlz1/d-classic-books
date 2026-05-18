package com.example.dclassicbooks.models;

public class Store {
    private String name;
    private String description;

    // Constructor
    public Store(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter methods
    public String getName() { return name; }
    public String getDescription() { return description; }
}


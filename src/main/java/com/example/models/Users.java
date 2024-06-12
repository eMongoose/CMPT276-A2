package com.example.models;

public class Users {
    private String name;
    private int width;
    private int height;
    private String color;

    // constructor does not take in parameters
    public Users() { 
    }

    // constructor that takes in parameters
    public Users(String name, int width, int height, String color) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    


}

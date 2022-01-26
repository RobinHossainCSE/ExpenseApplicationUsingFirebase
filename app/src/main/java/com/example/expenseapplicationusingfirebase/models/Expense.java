package com.example.expenseapplicationusingfirebase.models;

public class Expense {
    private String id;
    private String title;
    private double amount;
    private long timestamp;
    private String imageUrl;


    public Expense() {
        //empty constructor which is call by firebase
    }

    public Expense(String id, String title, double amount, long timestamp, String imageUrl) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.timestamp = timestamp;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

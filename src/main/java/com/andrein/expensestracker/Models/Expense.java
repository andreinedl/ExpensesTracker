package com.andrein.expensestracker.Models;

public class Expense {
    private int userId;
    private float price;
    private String description;
    private String category;
    private String date;

    public int getUserId() {
        return userId;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}

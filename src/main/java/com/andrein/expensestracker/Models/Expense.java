package com.andrein.expensestracker.Models;

public class Expense {
    private int id;
    private int userId;
    private float price;
    private String merchant;
    private String description;
    private String category;
    private Integer day;
    private Integer month;
    private Integer year;

    public int getId() {
        return id;
    }

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

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getEntireDate() {
        return day + "." + month + "." + year;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", price=" + price +
                ", merchant='" + merchant + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public Expense() {};
}

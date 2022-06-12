package com.example.backend2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock {
    private int id ;
    private String name ;
    private String description ;
    private double price;
    private int amount ;

    public Stock(int id,String name, String description, double price, int amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public Stock(ResultSet resultSet)throws SQLException {
        this.id  = resultSet.getInt("id");
        this.name = resultSet.getString("name");
        this.description = resultSet.getString("description");
        this.price = resultSet.getDouble("price");
        this.amount = resultSet.getInt("amount");
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

package com.example.backend2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    private int id ;
    private int product;
    private String type;
    private int amount;
    private String date ;

    public Transaction(int id, int product, String type, int amount, String date) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(ResultSet resultSet)throws SQLException {
        this.id = resultSet.getInt("id");
        this.product = resultSet.getInt("product_id");
        this.type = resultSet.getString("type");
        this.amount = resultSet.getInt("amount");
        this.date = resultSet.getString("date");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

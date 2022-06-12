package com.example.backend2.model;

import com.example.backend2.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockOperation {
    private Connection connection;
    public void insertStock(String name,String description,double price ,int amount) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Stock (name,description,price,amount) VALUE (?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,description);
            preparedStatement.setDouble(3,price);
            preparedStatement.setInt(4,amount);
//            preparedStatement.setInt(5,id);
            preparedStatement.execute();
        }
        finally {
            connection.close();
        }
    }
    public ArrayList<Stock> getStock() throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Stock ");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Stock> stock = new ArrayList<>();
            while (rs.next()){
                stock.add(new Stock(rs));
            }
            return stock;
        }
        finally {
            connection.close();
        }
    }
}

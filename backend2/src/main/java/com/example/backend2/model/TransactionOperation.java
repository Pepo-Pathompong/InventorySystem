package com.example.backend2.model;

import com.example.backend2.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TransactionOperation {
    private Connection connection;
    public void insertTransaction(int id,int product,String type ,int amount, String date) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Transaction(emp_id,product_id,type,amount,date) VALUE (?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,product);
            preparedStatement.setString(3,type);
            preparedStatement.setInt(4,amount);
            preparedStatement.setString(5,date);
            preparedStatement.execute();
        }
        finally {
            connection.close();
        }
    }
    public ArrayList<Transaction> getTransaction() throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Transaction ");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Transaction> transactions = new ArrayList<>();
            while (rs.next()){
                transactions.add(new Transaction(rs));
            }
            return transactions;
        }
        finally {
            connection.close();
        }
    }
}

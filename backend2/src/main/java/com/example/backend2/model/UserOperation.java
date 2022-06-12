package com.example.backend2.model;

import com.example.backend2.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserOperation {
    private Connection connection;
    public User insertUser(String username,String password,String email) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username,password,email) VALUE (?,?,?)");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
//            preparedStatement.setString(4,firstname);
//            preparedStatement.setString(5,lastname);
//            preparedStatement.setString(6,gender);
//            preparedStatement.setString(7,birthdate);
            System.out.println(username + password + email);
            preparedStatement.execute();
            return this.getUser(username);
        }
        finally {
            connection.close();
        }
    }
    public User getUser(String username) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs);
            }
            return user;
        }
        finally {
            connection.close();
        }
    }
    public User signInUser(String username,String password) throws SQLException{
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? ");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
//            preparedStatement.setString(3,email);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs);
            }
            System.out.println(user.getId());
            return user;
        }
        finally {
            connection.close();
        }
    }
    public User insertUpdateUser(int id,String firstname , String lastname, String gender ,String birthdate ) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname = ?, lastname = ?, gender = ?, birthdate = ? WHERE id = ?");
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,birthdate);
            preparedStatement.setInt(5,id);
            System.out.println(firstname + lastname + gender + birthdate+ id);
            preparedStatement.execute();
            return null;
        }
        finally {
            connection.close();
        }
    }
    public ArrayList<User> getUpdateUser() throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users ");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> user = new ArrayList<>();
            while (rs.next()){
                user.add(new User(rs));
                System.out.println(rs.getString("username"));
            }
            return user;
        }
        finally {
            connection.close();
        }
    }
    public void insertNewUser(String firstname, String lastname,String gender ,String birthdate) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (firstname,lastname,gender,birthdate) VALUE (?,?,?,?)");
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,gender);
            preparedStatement.setString(4,birthdate);
            System.out.println("NewUser : "+firstname + lastname + gender + birthdate);
            preparedStatement.execute();
        }
        finally {
            connection.close();
        }
    }
    public ArrayList<User> getNewUser() throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users ");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> newuser = new ArrayList<>();
            while (rs.next()){
                newuser.add(new User(rs));
            }
            return newuser;
        }
        finally {
            connection.close();
        }
    }
}

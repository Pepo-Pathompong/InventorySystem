package com.example.backend2.model;

import com.example.backend2.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoteOperation {
     Connection connection;
    public ArrayList<Note> getNote(int userId) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM users WHERE user_id = ?");
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Note> notes = new ArrayList<Note>();
            while (rs.next()){
                notes.add(new Note(rs));
            }
            return notes;
        }
        finally {
            connection.close();
        }
    }

    public void insertNote(int userId,String content) throws SQLException{
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO notes (content,user_id) VALUE (?,?)");
            preparedStatement.setString(1,content);
            preparedStatement.setInt(2,userId);
            ResultSet rs = preparedStatement.executeQuery();
        }
        finally {
            connection.close();
        }
    }

    public void updateNote(int userId,String content) throws SQLException{
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE notes SET content = ? WHERE  id = ?");
            preparedStatement.setString(1,content);
            preparedStatement.setInt(2,userId);
            ResultSet rs = preparedStatement.executeQuery();
        }
        finally {
            connection.close();
        }
    }
    public void deleteNote(int userId) throws SQLException{
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM notes WHERE id = ?");
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
        }
        finally {
            connection.close();
        }
    }

}

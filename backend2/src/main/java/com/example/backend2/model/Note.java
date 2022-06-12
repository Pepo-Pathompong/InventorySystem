package com.example.backend2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Note {
    private int id;
    private int userId;
    private String content;

    public Note(int id, int userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public Note(ResultSet rs) throws SQLException{
        this.id = rs.getInt("id");
        this.userId = rs.getInt("user_id");
        this.content = rs.getString("content");
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

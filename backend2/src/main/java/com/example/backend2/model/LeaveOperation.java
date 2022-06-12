package com.example.backend2.model;

import com.example.backend2.utility.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveOperation {
    private Connection connection;
    public Leave insertLeave( int requesterid, String description,
                             String start, String end, String status, int approverid) throws SQLException {
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Leave (requester_id,description,start_date,end_date,status,approver_id ) VALUE (?,?,?,?,?,?)");

            preparedStatement.setInt(1,requesterid);
            preparedStatement.setString(2,description);
            preparedStatement.setString(3,start);
            preparedStatement.setString(4,end);
            preparedStatement.setString(5,status);
            preparedStatement.setInt(6,approverid);
            preparedStatement.execute();
            return null;
        }
        finally {
            connection.close();
        }
    }
    public ArrayList<Leave> getLeave() throws SQLException{
        try {
            connection = DBconnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Leave ");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Leave> leave = new ArrayList<>();
            while (rs.next()){
                leave.add(new Leave(rs));
            }
            return leave;
        }
        finally {
            connection.close();
        }
    }
}

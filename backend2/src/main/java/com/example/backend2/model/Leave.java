package com.example.backend2.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Leave {
    private int requesterid ;
    private String description;
    private String start;
    private String end ;
    private String status;
    private int approverid;

    public Leave(int requesterid, String description, String start, String end, String status, int approverid) {
        this.requesterid = requesterid;
        this.description = description;
        this.start = start;
        this.end = end;
        this.status = status;
        this.approverid = approverid;
    }

    public Leave(ResultSet resultSet)throws SQLException {
        this.requesterid = resultSet.getInt("requester_id");
        this.description = resultSet.getString("description");
        this.start = resultSet.getString("start_date");
        this.end = resultSet.getString("end_date");
        this.status = resultSet.getString("status");
        this.approverid = resultSet.getInt("approver_id");
    }

    public int getRequesterid() {
        return requesterid;
    }

    public void setRequesterid(int requesterid) {
        this.requesterid = requesterid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getApproverid() {
        return approverid;
    }

    public void setApproverid(int approverid) {
        this.approverid = approverid;
    }
}

package com.example.backend2.controller;

import com.example.backend2.model.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "LeaveServlet", value = "/LeaveServlet")
@MultipartConfig
public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);
        try {
            LeaveOperation leaveOperation = new LeaveOperation();
            ArrayList<Leave> leave = leaveOperation.getLeave();
            out.print(gson.toJson(leave));
            response.setStatus(200);

        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);
        try {
            String requesterid = request.getParameter("requesterid");
            String description = request.getParameter("description");
            String start    = request.getParameter("start");
            String end      = request.getParameter("end");
            String status   = request.getParameter("status");
            String approverid = request.getParameter("approverid");
            System.out.println(requesterid + description + start + end + status +approverid);

            int requesterids = Integer.parseInt(requesterid);
            int approverids = Integer.parseInt(approverid);

            LeaveOperation leaveOperation = new LeaveOperation();
            leaveOperation.insertLeave(requesterids,description,start,end,status,approverids);
            response.setStatus(200);

        }catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
}

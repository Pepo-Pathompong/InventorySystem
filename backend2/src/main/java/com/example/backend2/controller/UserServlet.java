package com.example.backend2.controller;

import com.example.backend2.model.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);
        try {
            UserOperation userOperation = new UserOperation();
            ArrayList<User> newuser = userOperation.getNewUser();
            out.print(gson.toJson(newuser));
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
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String gender = request.getParameter("gender");
            String birthdate = request.getParameter("birthdate");
            System.out.println(firstname + lastname + gender + birthdate);

            UserOperation userOperation = new UserOperation();
            userOperation.insertNewUser(firstname,lastname,gender,birthdate);
            ArrayList<User> user = userOperation.getUpdateUser();
            out.print(gson.toJson(user));
            response.setStatus(200);

        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
}

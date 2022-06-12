package com.example.backend2.controller;

import com.example.backend2.model.ErrorResponse;
import com.example.backend2.model.User;
import com.example.backend2.model.UserOperation;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
@MultipartConfig
public class SignupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
//            String firstname = request.getParameter("firstname");
//            String lastname = request.getParameter("lastname");
//            String gender = request.getParameter("gender");
//            String birthdate = request.getParameter("birthdate");

            System.out.println(username + password + email);
            //first error-missing username,password
            if (username == null || password == null ){
                ErrorResponse errorResponse = new ErrorResponse("Username and password are required",500);
                response.setStatus(500);
                out.print(gson.toJson(errorResponse));
                return;
            }

            //second error-user already exist
            UserOperation userOperation = new UserOperation();
            User existUser = userOperation.getUser(username);
            if (existUser != null){
                ErrorResponse errorResponse = new ErrorResponse("Username " + username + " is used", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }
                //,firstname,lastname,gender,birthdate
            User user = userOperation.insertUser(username,password,email);
            out.print(gson.toJson(user));
            response.setStatus(201);
            request.getSession(true);


        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));

        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        super.doOptions(req, resp);
    }
}

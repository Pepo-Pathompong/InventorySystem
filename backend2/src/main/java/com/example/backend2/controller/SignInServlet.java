package com.example.backend2.controller;

import com.example.backend2.model.ErrorResponse;
import com.example.backend2.model.User;
import com.example.backend2.model.UserOperation;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignInServlet", value = "/SignInServlet")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
//            String email = request.getParameter("email");

            if (username == null || password == null ){
                ErrorResponse errorResponse = new ErrorResponse("Username and password are required",400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }
            User user = new UserOperation().signInUser(username,password);
            if (user == null){
                ErrorResponse errorResponse = new ErrorResponse("Username and/or password are not correct",600);
                response.setStatus(600);
                out.print(gson.toJson(errorResponse));
                return;
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("userId",user.getId());
            response.setStatus(200);

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

package com.example.backend2.controller;

import com.example.backend2.model.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Middleware {
    public static int authCheck(HttpServletRequest request, HttpServletResponse response)throws  IOException,ErrorResponse{
        HttpSession session = request.getSession(false);
        if (session == null){
            throw new ErrorResponse("unauthorized",401);
        }
        String userId = session.getAttribute("userId").toString();
        return Integer.parseInt(userId);
    }
    public static void setCORS(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length");
    }
}

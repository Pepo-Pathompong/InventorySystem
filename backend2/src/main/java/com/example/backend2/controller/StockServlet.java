package com.example.backend2.controller;

import com.example.backend2.model.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "StockServlet", value = "/StockServlet")
public class StockServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);
        try {
            StockOperation stockOperation = new StockOperation();
            ArrayList<Stock> stock = stockOperation.getStock();
            out.print(gson.toJson(stock));
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
        Middleware.setCORS(request, response);
        try {
//            int id = Middleware.authCheck(request,response);
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String amount = request.getParameter("amount");
            if (name == null || description == null || price == null || amount == null ) {
                ErrorResponse errorResponse = new ErrorResponse(" Got null", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }
            double prices = Double.parseDouble(price);
            int amounts = Integer.parseInt(amount);


            System.out.println(name + description + price + amount);
//            HttpSession session = request.getSession(false);
//            int id = (Integer) session.getAttribute("userId");
            StockOperation stockOperation = new StockOperation();
            stockOperation.insertStock(name,description,prices,amounts);
            response.setStatus(200);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
}

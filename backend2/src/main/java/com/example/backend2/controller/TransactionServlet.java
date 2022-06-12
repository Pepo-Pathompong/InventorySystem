package com.example.backend2.controller;

import com.example.backend2.model.*;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TransactionServlet", value = "/TransactionServlet")
@MultipartConfig
public class TransactionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request,response);
        try {
            TransactionOperation transactionOperation = new TransactionOperation();
            ArrayList<Transaction> transactions = transactionOperation.getTransaction();
            out.print(gson.toJson(transactions));
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
//            HttpSession session = request.getSession(false);
//            int id = (Integer) session.getAttribute("userId");
//            String ids = request.getParameter("id");
            String products = request.getParameter("product");
            String type = request.getParameter("type");
            String amounts = request.getParameter("amount");
            String date = request.getParameter("date");
            if (products == null || type == null || amounts == null || date == null ) {
                ErrorResponse errorResponse = new ErrorResponse(" Got null", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
                return;
            }
//            int id = Integer.parseInt(ids);
            int product = Integer.parseInt(products);
            int amount = Integer.parseInt(amounts);


            TransactionOperation transactionOperation = new TransactionOperation();
            transactionOperation.insertTransaction(1,product,type,amount,date);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }
}

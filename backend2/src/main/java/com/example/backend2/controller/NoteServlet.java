package com.example.backend2.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.example.backend2.model.ErrorResponse;
import com.example.backend2.model.Note;
import com.example.backend2.model.NoteOperation;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "NoteServlet", value = "/NoteServlet")
public class NoteServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            //gson pure string to response
            response.setContentType("application/json");
            Middleware.setCORS(request,response);
            try {
                int userId = Middleware.authCheck(request,response);
                NoteOperation noteOperation = new NoteOperation();

                ArrayList<Note> notes = noteOperation.getNote(userId);
                out.print(gson.toJson(notes));
            }catch (ErrorResponse e){
                response.setStatus(e.getStatusCode());
                out.print(gson.toJson(e));
            } catch (Exception e){
                ErrorResponse errorResponse = new ErrorResponse(e.toString(),500);
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
                int userId = Middleware.authCheck(request, response);
                String content = request.getParameter("content");
                if (content==null){
                    ErrorResponse errorResponse = new ErrorResponse("content is empty",500);
                    response.setStatus(500);
                    out.print(gson.toJson(errorResponse));
                    return;
                }
                NoteOperation noteOperation = new NoteOperation();
                noteOperation.insertNote(userId,content);

                ArrayList<Note> notes = noteOperation.getNote(userId);
                response.setStatus(201);
                out.print(gson.toJson(notes));

            }catch (ErrorResponse e){
                response.setStatus(e.getStatusCode());
                out.print(gson.toJson(e));
            }
            catch (Exception e){
                ErrorResponse errorResponse = new ErrorResponse(e.toString(),500);
                response.setStatus(500);
                out.print(gson.toJson(errorResponse));
            }
        }

        @Override
        protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            response.setContentType("application/json");
            Middleware.setCORS(request,response);
            try {
                String id = request.getParameter("id");
                String content = request.getParameter("content");
                if (id == null || content == null){
                    ErrorResponse errorResponse = new ErrorResponse("Content or id is missing",400);
                    response.setStatus(errorResponse.getStatusCode());
                    out.print(gson.toJson(errorResponse));
                    return;
                }
                NoteOperation noteOperation = new NoteOperation();
                noteOperation.updateNote(Integer.valueOf(id),content);
                response.setStatus(400);

            }catch (Exception e){
                e.printStackTrace();
                ErrorResponse errorResponse = new ErrorResponse(e.toString(),400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
            }
        }

        @Override
        protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            //gson pure string to response
            response.setContentType("application/json");
            Middleware.setCORS(request,response);
            try {
                String  id = request.getParameter("id");
                if (id==null){
                    ErrorResponse errorResponse = new ErrorResponse("id is missing",500);
                    response.setStatus(500);
                    out.print(gson.toJson(errorResponse));
                    return;
                }
                NoteOperation noteOperation = new NoteOperation();
                noteOperation.deleteNote(Integer.valueOf(id));
                response.setStatus(200);
            }catch (Exception e){
                e.printStackTrace();
                ErrorResponse errorResponse = new ErrorResponse(e.toString(),500);
                response.setStatus(500);
                out.print(gson.toJson(errorResponse));
            }
        }
}

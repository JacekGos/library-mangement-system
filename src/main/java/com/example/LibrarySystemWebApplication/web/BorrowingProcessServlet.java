package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.model.LibraryUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "borrrowingProcess", value = "/borrowingProcess")
public class BorrowingProcessServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrow":
                doPost(request, response);
                break;
            default:

                break;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        String action = (String)request.getAttribute("action");

        switch (action) {
            case "borrow":
                sendRequest(request, response);
                break;
            default:

                break;
        }

    }

    private void sendRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

}

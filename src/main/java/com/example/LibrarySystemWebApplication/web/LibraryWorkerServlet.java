package com.example.LibrarySystemWebApplication.web;

import com.example.LibrarySystemWebApplication.dao.LibraryWorkerDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LibraryWorkerServlet extends HttpServlet {

    private LibraryWorkerDao libraryWorkerDao;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getServletPath();

        switch (action) {

        }

    }

}

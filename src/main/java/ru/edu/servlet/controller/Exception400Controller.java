package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(name = "Exception400Controller", urlPatterns = {"/exception400Controller"})
@Slf4j
public class Exception400Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("needAuthPage.jsp");
        Cookie code = new Cookie("code", "400");
        code.setMaxAge(60*60*24);
        resp.addCookie(code);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("needAuthPage.jsp");
        Cookie code = new Cookie("code", "400");
        code.setMaxAge(60*60*24);
        resp.addCookie(code);
        requestDispatcher.forward(req, resp);
    }
}

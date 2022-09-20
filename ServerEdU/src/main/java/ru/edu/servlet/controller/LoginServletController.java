package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(name = "LoginServletController", urlPatterns = {"/loginServlet"})
public class LoginServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null) {
            log.warn("Login or pwd is empty!");
            resp.sendError(400, "Login or password empty");
        } else {
            log.info("method POST with login: {}", username);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            req.getSession().setAttribute("user", username);
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(" method: doGet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("loginPage.jsp");
        requestDispatcher.forward(req, resp);
    }
}

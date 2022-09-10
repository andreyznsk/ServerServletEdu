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

@WebServlet(name = "LoginServletController", urlPatterns = {"/loginServlet"})
@Slf4j
public class LoginServletController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = Optional.ofNullable(req.getParameter("username")).orElse("");
        String password = Optional.ofNullable(req.getParameter("password")).orElse("");
        if (username.isEmpty() || password.isEmpty()) {
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

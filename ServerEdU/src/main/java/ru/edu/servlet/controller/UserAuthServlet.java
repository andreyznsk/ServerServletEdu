package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "LoginServletController", urlPatterns = {"/userAuthServlet"})
public class UserAuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if (username == null) {
            log.warn("Login is empty!");
            resp.sendError(400, "Login or password empty");
        } else {
            log.info("method POST with login: {}", username);
            req.setAttribute("user", username);

            req.getRequestDispatcher("user_greateeng.jsp").forward(req, resp);
        }
    }
}

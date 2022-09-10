package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CalculatorServletController", urlPatterns = {"/calculator"})
@Slf4j
public class CalculatorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String digital = req.getParameter("digital");
        log.info("get digital: {}", digital);
        int num = Integer.parseInt(digital);
        HttpSession session = req.getSession();
        if (session.isNew()) {
            session.setMaxInactiveInterval(60);
            session.setAttribute("result", num);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("calculatorPageSecondNumber.jsp");
            requestDispatcher.forward(req, resp);
        }
        Object result = session.getAttribute("result");
        if (result == null) {
            session.setAttribute("result", num);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("calculatorPageSecondNumber.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            num += (Integer) result;
            session.setAttribute("result", num);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("resultPage.jsp");
            requestDispatcher.forward(req, resp);
        }


    }

}

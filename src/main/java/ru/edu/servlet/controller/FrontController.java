package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String stage = Optional.ofNullable(req.getParameter("stage")).orElse("");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        RequestDispatcher requestDispatcher;
        switch (stage) {
            case "catalog":
                requestDispatcher = req.getRequestDispatcher("/start/catalog");
                requestDispatcher.include(req, resp);
                break;
            case "cart":
                requestDispatcher = req.getRequestDispatcher("/start/cart");
                requestDispatcher.include(req, resp);
                break;
            default:
                out.println("There is no param: \"" + stage + "\"");
                out.println("set \"stage\" param to catalog or cart");
                break;
        }
        out.println("<h1>" + getServletName() + "</h1>");
        out.println("</body></html>");
        out.close();
    }

}

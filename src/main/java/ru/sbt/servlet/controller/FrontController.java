package ru.sbt.servlet.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String queryString = req.getParameter("stage");
        if(Objects.isNull(queryString)){
            resp.setContentType("text/html");//setting the content type
            PrintWriter pw = resp.getWriter();//get the stream to write the data
            pw.println("<html><body>");
            pw.println("set \"stage\" param to catalog or cart");
            pw.println("</body></html>");
            pw.close();
        }
        switch (queryString) {
            case "catalog":
                resp.sendRedirect(req.getContextPath() + "/catalog");
                break;
            case "cart":
                resp.sendRedirect(req.getContextPath() + "/cart");
                break;
            default:
                resp.setContentType("text/html");//setting the content type
                PrintWriter pw = resp.getWriter();//get the stream to write the data
                pw.println("<html><body>");
                pw.println("Unknown param");
                pw.println("</body></html>");
                pw.close();
        }
    }

}

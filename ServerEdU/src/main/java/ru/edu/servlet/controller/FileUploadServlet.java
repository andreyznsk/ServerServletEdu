package ru.edu.servlet.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "FileUploadServlet", urlPatterns = {"/fileuploadservlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100, // 100 MB
        location = "/tmp"
)
@Slf4j
public class FileUploadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Servlet:{}, method: doPost", FileUploadServlet.class);
        final Collection<Part> parts = request.getParts();
        StringBuilder sb = new StringBuilder();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("FileUploadForm.jsp");

        if (parts.isEmpty()) {
            sb.append("No parts provided.");
        } else {
            for (Part part : parts) {
                final String submittedFileName = part.getSubmittedFileName();
                final String partName = part.getName();
                if (submittedFileName.isBlank()) {
                    sb.append("<h3>File name is empty for ").append(partName).append("</h3>");
                } else {
                    sb.append(" getSubmittedFileName: ").append(submittedFileName);
                    sb.append(" getName: ").append(partName).append(",<br>");
                    part.write(submittedFileName);
                    sb.append("<h3>The file: ").append(submittedFileName).append(" uploaded successfully.</h3>");
                }
            }
        }
        request.getSession().setAttribute("result", sb.toString());
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Servlet:{}, method: doGet", FileUploadServlet.class);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("FileUploadForm.jsp");
        requestDispatcher.forward(request, response);
    }

}
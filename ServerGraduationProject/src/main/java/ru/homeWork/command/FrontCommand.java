package ru.homeWork.command;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String user;
    protected String sessionId;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            String user,
            String session) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.user = user;
        this.sessionId = session;
    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/jsp/%s.jsp", target);
        context.getRequestDispatcher(target).forward(request, response);
    }
}

package ru.homeWork.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import ru.homeWork.command.FrontCommand;
import ru.homeWork.command.UnknownCommand;

import java.io.IOException;

@Slf4j
@WebServlet(name = "FrontController", urlPatterns = {"/", "/index"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter("command");
        HttpSession session = request.getSession();

        if (commandStr == null) {
            if (session.isNew()) {
                session.setMaxInactiveInterval(600);
                String user = (String) session.getAttribute("user");
                if (user == null) {
                    request.getRequestDispatcher("/index?command=Auth").forward(request,response);
                } else {
                    request.getRequestDispatcher("/index?command=ProductViewCommand").forward(request,response);
                }
            }
        } else {
            FrontCommand command = getCommand(commandStr);
            command.init(getServletContext(), request, response);
            command.process();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandStr = req.getParameter("command");
        FrontCommand command = getCommand(commandStr);
        command.init(getServletContext(), req, resp);
        command.process();
        String username = (String)req.getSession().getAttribute("username");
        log.info("User logg in: {}",username);
        resp.sendRedirect(req.getContextPath() + "index?command=ProductView");
    }

    private FrontCommand getCommand(String command) {
        try {
            Class<?> type = Class.forName(String.format(
                    "ru.homeWork.command.%sCommand",
                    command));
            return type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

}

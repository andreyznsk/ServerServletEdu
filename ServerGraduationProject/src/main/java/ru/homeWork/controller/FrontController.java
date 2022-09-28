package ru.homeWork.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import ru.homeWork.command.FrontCommand;
import ru.homeWork.command.UnknownCommand;
import ru.homeWork.dataSourceConnectionConfig.TomCatDataSource;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@WebServlet(name = "FrontController", urlPatterns = {"", "/", "/index"}, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        TomCatDataSource.conf();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter("command");
        HttpSession session = request.getSession();
        String userName = getUserNameFromCookie(request.getCookies());
        request.setAttribute("username", userName);

        log.info("Receive get from path info: {}, with command: {}, and user: {}", request.getPathInfo(), commandStr, userName);

        if (commandStr == null || userName == null) {
            if (session.isNew()) {
                session.setMaxInactiveInterval(600);
            }
            if (userName == null) {
                request.getRequestDispatcher("/index?command=Auth").forward(request, response);
            } else {
                request.getRequestDispatcher("/index?command=ProductView").forward(request, response);
            }

        } else {
            FrontCommand command = getCommand(commandStr);
            command.init(getServletContext(), request, response, userName, session.getId());
            command.process();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = getUserNameFromCookie(req.getCookies());
        if (userName == null) {
            resp.sendRedirect(req.getContextPath() + "/index");
        } else {
            log.info("User logg in: {}", userName);
            String commandStr = req.getParameter("command");
            FrontCommand command = getCommand(commandStr);
            command.init(getServletContext(), req, resp, userName, req.getSession().getId());
            command.process();
        }

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

    private String getUserNameFromCookie(Cookie[] cookies) {

        return Arrays.stream(cookies).filter(cookie -> "username".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

    }


}

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

import static ru.homeWork.command.CommandTypeAndParam.AUTH;
import static ru.homeWork.command.CommandTypeAndParam.COMMAND_PARAM;
import static ru.homeWork.command.CommandTypeAndParam.PRODUCT_VIEW;
import static ru.homeWork.command.CommandTypeAndParam.USER_PARAM;

@Slf4j
@WebServlet(name = "FrontController", urlPatterns = {"", "/", "/index"}, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    @Override
    public void init() {
        TomCatDataSource.conf();
        log.info("\n\t==========SERVER STARTED============");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter(COMMAND_PARAM.getCommand());
        HttpSession session = request.getSession();
        String userName = getUserNameFromCookie(request.getCookies());
        request.setAttribute(USER_PARAM.getCommand(), userName);

        log.info("Receive get from path info: {}, with command: {}, and user: {}", request.getPathInfo(), commandStr, userName);

        if (userName == null) {
            if (session.isNew()) {
                session.setMaxInactiveInterval(600);
            }
            FrontCommand command = getCommand(AUTH.getCommand());
            command.init(getServletContext(), request, response, userName, session.getId());
            command.process();
        }
        else if (commandStr != null) {
            FrontCommand command = getCommand(commandStr);
            command.init(getServletContext(), request, response, userName, session.getId());
            command.process();
        } else {
            FrontCommand command = getCommand(PRODUCT_VIEW.getCommand());
            command.init(getServletContext(), request, response, userName, session.getId());
            command.process();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = getUserNameFromCookie(request.getCookies());
        if (userName == null) {
            FrontCommand command = getCommand(AUTH.getCommand());
            command.init(getServletContext(), request, response, null, request.getSession().getId());
            command.process();
        } else {
            log.info("User logg in: {}", userName);
            String commandStr = request.getParameter(COMMAND_PARAM.getCommand());
            FrontCommand command = getCommand(commandStr);
            command.init(getServletContext(), request, response, userName, request.getSession().getId());
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
                    .getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }

    private String getUserNameFromCookie(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        } else {
            return Arrays.stream(cookies).filter(cookie -> USER_PARAM.getCommand().equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
    }


}

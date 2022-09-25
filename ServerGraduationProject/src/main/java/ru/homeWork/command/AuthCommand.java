package ru.homeWork.command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Locale;

@Slf4j
public class AuthCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        String method = request.getMethod();
        switch (method) {
            case "GET": {
                forward("loginPage");
                break;
            }
            case "POST": {
                String username = request.getParameter("username");
                if (username == null) {
                    log.warn("UserName is empty!");
                    response.sendError(400, "UserName or password empty");
                } else {
                    log.info("method POST with login: {}", username);
                    request.getSession().setAttribute("username", username);
                }
                break;
            }

            default: {
                log.error("unsupported method!");
                forward("/index");
            }
        }
    }
}

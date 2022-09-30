package ru.homeWork.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static ru.homeWork.command.CommandTypeAndParam.INDEX_CONTEXT;
import static ru.homeWork.command.CommandTypeAndParam.USER_PARAM;

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
                String username = request.getParameter(USER_PARAM.getCommand());
                if (username == null) {
                    log.warn("UserName is empty!");
                    response.sendError(400, "UserName or password empty");
                } else {
                    log.info("method POST with login: {}", username);
                    Cookie user = new Cookie(USER_PARAM.getCommand(), username);
                    response.addCookie(user);
                    response.sendRedirect( request.getContextPath() + INDEX_CONTEXT.getCommand());
                }
                break;
            }

            default: {
                log.error("unsupported method!");
                forward(INDEX_CONTEXT.getCommand());
            }
        }
    }
}

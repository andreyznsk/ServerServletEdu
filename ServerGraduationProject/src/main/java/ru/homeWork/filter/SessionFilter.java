
package ru.homeWork.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;

@Slf4j
@WebFilter(
        filterName = "SessionFilter",
        urlPatterns = {"*"}
)
public class SessionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        log.info("Receive request host:{}", request.getRemoteHost());

        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    MDC.put("JSESSIONID", cookie.getValue());
                }
            }
        }
        try {
            chain.doFilter(request, response);
            MDC.remove("JSESSIONID");
        } catch (IOException | ServletException e) {
            log.error("Filter error", e);
            MDC.remove("JSESSIONID");
            try {
                ((HttpServletResponse) response).sendError(500, "Server internal error");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}
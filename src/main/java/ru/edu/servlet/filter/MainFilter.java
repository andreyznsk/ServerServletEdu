package ru.edu.servlet.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
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
import java.util.regex.Pattern;

@Slf4j
@WebFilter(
        filterName = "MainFilter",
        urlPatterns = {"/calculator"}
)
public class MainFilter implements Filter {

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        for (Cookie cookie : ((HttpServletRequest) request).getCookies()) {
            if ("JSESSIONID".equals(cookie.getName())) {
                MDC.put("JSESSIONID", cookie.getValue());
            }
        }

        try {
            String digital = request.getParameter("digital");

            if (!pattern.matcher(digital).matches()) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("calculatorErrorExplanation.jsp");
                requestDispatcher.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            log.error("Filter error",e);
            MDC.remove("JSESSIONID");
            try {
                ((HttpServletResponse)response).sendError(500,"Server internal error");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}